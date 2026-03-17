package simplepets.brainsynder.commands.list;

import org.bsdevelopment.pluginutils.command.CommandBuilder;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.commands.PetCommandClass;
import simplepets.brainsynder.utils.Premium;

public class PremiumCommand implements PetCommandClass {

    @Override
    public CommandBuilder build() {
        return CommandBuilder.create("premium")
                .withPermission("pet.commands.premium")
                .withDescription("Shows the information of the user who purchased the resource")
                .executes((sender, args) -> {
                    sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX) + "§7Purchase Users ID: " + Premium.USER_ID);
                    sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX) + "§7Premium Resource ID: " + Premium.USER_ID);
                    sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX) + "§7Download ID: " + Premium.UNIQUE_DOWNLOAD_ID + "(Unique for all downloads)");
                });
    }
}
