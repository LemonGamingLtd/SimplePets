package simplepets.brainsynder.commands.list;

import org.bsdevelopment.pluginutils.command.CommandBuilder;
import org.bsdevelopment.pluginutils.command.CommandPermission;
import org.bsdevelopment.pluginutils.command.arguments.PlayerArgument;
import org.bsdevelopment.pluginutils.command.arguments.StringArgument;
import org.bukkit.entity.Player;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.commands.PetCommandClass;
import simplepets.brainsynder.utils.RenameType;

public class RenameCommand implements PetCommandClass {

    @Override
    public CommandBuilder build() {
        return CommandBuilder.create("rename")
                .withPermission("pet.commands.rename")
                .withDescription("Renames the selected pet type")
                .withRequirement(sender -> sender instanceof Player)
                .withArguments(new PlayerArgument("player")
                        .setOptional(true)
                        .withPermission(CommandPermission.of("pet.commands.rename.other")))
                .withArguments(ACCESSIBLE_PET_TYPES)
                .withArguments(new StringArgument("name")
                        .setOptional(true))
                .executesPlayer((player, args) -> {
                    Player target = args.getOrDefault("player", player);

                    if (target != player && !player.hasPermission("pet.commands.rename.other")) {
                        player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.NO_PERMISSION));
                        return;
                    }

                    PetType type = args.get("type");

                    if (!SimplePets.getSpawnUtil().isRegistered(type)) {
                        player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PET_NOT_REGISTERED)
                                .replace("{type}", type.getName()));
                        return;
                    }

                    SimplePets.getUserManager().getPetUser(target).ifPresent(user -> {
                        // Admin renaming for another player
                        if (target != player) {
                            if (args.has("name")) {
                                user.setPetName(args.get("name"), type);
                            }
                            return;
                        }

                        RenameType rename = RenameType.getType(ConfigOption.RENAME_TYPE.get(), RenameType.ANVIL);
                        switch (rename) {
                            case CHAT:
                                PetCore.getInstance().getRenameManager().renameViaChat(user, type);
                                break;
                            case COMMAND:
                                if (!args.has("name")) {
                                    player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX)
                                            + " §cUsage: /pet rename [player] <type> <name>");
                                    return;
                                }
                                user.setPetName(args.get("name"), type);
                                break;
                            case ANVIL:
                                PetCore.getInstance().getRenameManager().renameViaAnvil(user, type);
                                break;
                            case SIGN:
                                PetCore.getInstance().getRenameManager().renameViaSign(user, type);
                                break;
                            case DIALOG:
                                PetCore.getInstance().getRenameManager().renameViaDialog(user, type);
                                break;
                        }
                    });
                });
    }
}
