package simplepets.brainsynder.commands.list;

import org.bsdevelopment.pluginutils.command.CommandBuilder;
import org.bsdevelopment.pluginutils.command.CommandPermission;
import org.bsdevelopment.pluginutils.command.arguments.PlayerArgument;
import org.bukkit.entity.Player;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.commands.PetCommandClass;

import java.util.concurrent.atomic.AtomicInteger;

public class RemoveCommand implements PetCommandClass {

    @Override
    public CommandBuilder build() {
        return CommandBuilder.create("remove")
                .withPermission("pet.commands.remove")
                .withDescription("Remove your pet or another players")
                .withRequirement(sender -> sender instanceof Player)
                .withArguments(new PlayerArgument("player")
                        .setOptional(true)
                        .withPermission(CommandPermission.of("pet.commands.remove.other")))
                .withArguments(ALL_PET_TYPES.setOptional(true))
                .executesPlayer((player, args) -> {
                    Player target = args.getOrDefault("player", player);

                    if (target != player && !player.hasPermission("pet.commands.remove.other")) {
                        player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.NO_PERMISSION));
                        return;
                    }

                    if (args.has("type")) {
                        PetType type = args.get("type");
                        SimplePets.getUserManager().getPetUser(target).ifPresent(user -> {
                            user.removePet(type);
                            player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.REMOVED_PET)
                                    .replace("{type}", type.getName()));
                        });
                    } else {
                        AtomicInteger count = new AtomicInteger(0);
                        SimplePets.getUserManager().getPetUser(target).ifPresent(user -> {
                            for (PetType type : PetType.values())
                                if (user.removePet(type)) count.incrementAndGet();
                        });
                        player.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.REMOVED_ALL_PETS)
                                .replace("{count}", String.valueOf(count.get())));
                    }
                });
    }
}
