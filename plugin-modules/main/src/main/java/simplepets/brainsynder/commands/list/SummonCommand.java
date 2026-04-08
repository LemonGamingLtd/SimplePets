package simplepets.brainsynder.commands.list;

import lib.brainsynder.nbt.JsonToNBT;
import lib.brainsynder.nbt.StorageTagCompound;
import lib.brainsynder.nms.Tellraw;
import org.bsdevelopment.pluginutils.command.CommandBuilder;
import org.bsdevelopment.pluginutils.command.CommandPermission;
import org.bsdevelopment.pluginutils.command.arguments.PlayerArgument;
import org.bsdevelopment.pluginutils.command.arguments.StorageTagArgument;
import org.bsdevelopment.pluginutils.command.arguments.suggestions.ArgumentSuggestions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.ISpawnUtil;
import simplepets.brainsynder.api.SpawnResult;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.IEntityControllerPet;
import simplepets.brainsynder.api.event.inventory.PetSelectTypeEvent;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.commands.PetCommandClass;
import simplepets.brainsynder.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class SummonCommand implements PetCommandClass {

    @Override
    public CommandBuilder build() {
        return CommandBuilder.create("summon")
                .withAliases("spawn")
                .withPermission("pet.commands.summon")
                .withDescription("Spawns a pet for the player or selected player")
                .withSubcommand(buildAllCommand())
                .withArguments(ACCESSIBLE_PET_TYPES)
                .withArguments(new PlayerArgument("player")
                        .setOptional(true)
                        .withPermission(CommandPermission.of("pet.commands.summon.other")))
                .withArguments(new StorageTagArgument("nbt")
                        .setOptional(true)
                        .withPermission(CommandPermission.of("pet.commands.summon.nbt"))
                        .replaceSuggestions(ArgumentSuggestions.of(info -> {
                            List<String> suggestions = new ArrayList<>();
                            suggestions.add("{}");

                            Player player = null;
                            if (info.previousArgs() != null && info.previousArgs().has("player")) {
                                player = info.previousArgs().get("player");
                            } else if (info.sender() instanceof Player p) {
                                player = p;
                            }
                            if (player == null) return suggestions;

                            PetType type = info.previousArgs() != null ? info.previousArgs().get("type") : null;
                            if (type == null || type == PetType.UNKNOWN) return suggestions;

                            Optional<PetUser> user = SimplePets.getUserManager().getPetUser(player);
                            if (user.isEmpty()) return suggestions;

                            user.get().getPetEntity(type).ifPresent(entityPet -> {
                                String compoundStr = entityPet.asCompound().toString();
                                if (!compoundStr.equals("{}")) suggestions.add(compoundStr);
                            });

                            return suggestions;
                        })))
                .executes((sender, args) -> {
                    ISpawnUtil spawner = PetCore.getInstance().getSpawnUtil();
                    if (spawner == null) return;

                    PetType type = args.get("type");

                    if (type.isInDevelopment() && !ConfigOption.PET_TOGGLES_DEV_MOBS.get()) {
                        sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PET_IN_DEVELOPMENT)
                                .replace("{type}", type.getName()));
                        return;
                    }

                    Player target;
                    if (args.has("player")) {
                        target = args.get("player");
                    } else if (sender instanceof Player p) {
                        target = p;
                    } else {
                        sender.sendMessage("§cYou must specify a player when running from console.");
                        return;
                    }

                    StorageTagCompound compound = new StorageTagCompound();
                    if (args.has("nbt") && sender.hasPermission("pet.commands.summon.nbt")) {
                        org.bsdevelopment.nbt.StorageTagCompound nbtArg = args.get("nbt");
                        try {
                            compound = JsonToNBT.getTagFromJson(nbtArg.toString());
                        } catch (Exception e) {
                            sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.INVALID_NBT));
                            return;
                        }
                    }

                    StorageTagCompound finalCompound = compound;
                    Utilities.applyPetDataDefaults(type, finalCompound);

                    Player finalTarget = target;
                    PetCore.getInstance().getUserManager().getPetUser(target.getUniqueId()).ifPresent(user -> {
                        if (!user.canSpawnMorePets() && finalTarget == sender) {
                            sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.CANT_SPAWN_MORE_PETS));
                            return;
                        }

                        if (!user.canSpawnMorePets()
                                && !ConfigOption.MISC_TOGGLES_CONSOLE_BYPASS_LIMIT.get()
                                && !(sender instanceof Player)) {
                            finalTarget.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.CANT_SPAWN_MORE_PETS));
                            return;
                        }

                        if (finalTarget == sender) {
                            PetSelectTypeEvent event = new PetSelectTypeEvent(type, user);
                            Bukkit.getServer().getPluginManager().callEvent(event);
                            if (event.isCancelled()) return;
                        }

                        SpawnResult<IEntityPet> entityPet = spawner.spawnEntityPet(type, user, finalCompound);
                        if (!entityPet.isSuccess()) {
                            if (entityPet.isFailure()) {
                                Tellraw.fromLegacy(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.FAILED_SUMMON, false)
                                        .replace("{type}", type.getName()))
                                        .tooltip(entityPet.failMessage()).send(sender);
                                return;
                            }
                            sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.FAILED_SUMMON)
                                    .replace("{type}", type.getName()));
                            return;
                        }

                        if (type == PetType.ARMOR_STAND) {
                            ((IEntityControllerPet) entityPet.value()).getVisibleEntity().applyCompound(finalCompound);
                        }
                        sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.SUMMONED_PET)
                                .replace("{type}", type.getName()));
                    });
                });
    }

    public CommandBuilder buildAllCommand() {
        return CommandBuilder.create("all")
                .withPermission("pet.commands.summon.all")
                .withDescription("Spawns all available pet types for yourself")
                .withRequirement(sender -> sender instanceof Player)
                .executesPlayer((player, args) -> {
                    ISpawnUtil spawner = PetCore.getInstance().getSpawnUtil();
                    if (spawner == null) return;

                    AtomicInteger count = new AtomicInteger(0);
                    for (PetType type : PetType.values()) {
                        SimplePets.getPetConfigManager().getPetConfig(type).ifPresent(config -> {
                            if (!config.isEnabled()) return;
                            if (!type.isSupported()) return;
                            if (!SimplePets.getSpawnUtil().isRegistered(type)) return;
                            count.getAndIncrement();
                            PetCore.getInstance().getUserManager().getPetUser(player.getUniqueId())
                                    .ifPresent(user -> spawner.spawnEntityPet(type, user));
                        });
                    }

                    player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.SUMMONED_ALL_PETS)
                            .replace("{count}", String.valueOf(count.get())));
                });
    }
}
