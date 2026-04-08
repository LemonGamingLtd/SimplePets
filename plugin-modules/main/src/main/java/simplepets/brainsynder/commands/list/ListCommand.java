package simplepets.brainsynder.commands.list;

import lib.brainsynder.nms.Tellraw;
import net.md_5.bungee.api.ChatColor;
import org.bsdevelopment.pluginutils.command.CommandBuilder;
import simplepets.brainsynder.api.pet.IPetConfig;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.commands.PetCommandClass;
import simplepets.brainsynder.utils.Utilities;

import java.util.Optional;

public class ListCommand implements PetCommandClass {

    @Override
    public CommandBuilder build() {
        return CommandBuilder.create("list")
                .withPermission("pet.commands.list")
                .withDescription("Lists all the different pet types")
                .executes((sender, args) -> {
                    Tellraw raw = Tellraw.getInstance("Pet List: ").color("#d1c9c9");

                    for (PetType type : PetType.values()) {
                        if (type == PetType.UNKNOWN) continue;
                        Optional<IPetConfig> config = SimplePets.getPetConfigManager().getPetConfig(type);
                        String tooltip = "";
                        ChatColor color = ChatColor.GREEN;
                        if (config.isPresent() && (!config.get().isEnabled())) {
                            color = ChatColor.RED;
                            tooltip = color + "DISABLED";
                        } else if (!type.isSupported()) {
                            color = ChatColor.GOLD;
                            tooltip = color + "NOT SUPPORTED";
                        } else if (!SimplePets.getSpawnUtil().isRegistered(type)) {
                            color = ChatColor.YELLOW;
                            tooltip = color + "NOT REGISTERED";
                        } else if (type.isInDevelopment() && (!ConfigOption.PET_TOGGLES_DEV_MOBS.get())) {
                            color = ChatColor.GRAY;
                            tooltip = color + "IN DEVELOPMENT";
                        }
                        if (ConfigOption.MISC_TOGGLES_LIST_RESTRICTIONS.get()) {
                            if (color != ChatColor.GREEN) continue;
                            if (!Utilities.hasPermission(sender, type.getPermission())) continue;
                        }

                        raw.then(type.getName()).color(color);
                        if (!tooltip.isEmpty()) raw.tooltip(tooltip);
                        raw.then(", ").color(ChatColor.of("#d1c9c9"));
                    }

                    raw.send(sender);
                });
    }
}
