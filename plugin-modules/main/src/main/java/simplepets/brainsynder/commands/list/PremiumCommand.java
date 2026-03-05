package simplepets.brainsynder.commands.list;

import lib.brainsynder.commands.annotations.ICommand;
import org.bukkit.command.CommandSender;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.commands.Permission;
import simplepets.brainsynder.commands.PetSubCommand;
import simplepets.brainsynder.utils.Premium;

@ICommand(
    name = "premium",
    description = "Shows the information of the user who purchased the resource"
)
@Permission(permission = "premium", adminCommand = true)
public class PremiumCommand extends PetSubCommand {

    public PremiumCommand(PetCore plugin) {
        super(plugin);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX) + "§7Purchase Users ID: " + Premium.USER_ID);
        sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX) + "§7Premium Resource ID: " + Premium.USER_ID);
        sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX) + "§7Download ID: " + Premium.UNIQUE_DOWNLOAD_ID + "(Unique for all downloads)");
    }
}
