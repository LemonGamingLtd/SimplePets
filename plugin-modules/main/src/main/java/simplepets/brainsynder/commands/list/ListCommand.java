package simplepets.brainsynder.commands.list;

import org.bsdevelopment.pluginutils.chat.TellrawMessage;
import org.bsdevelopment.pluginutils.chat.decoration.NamedTextColor;
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
                    TellrawMessage raw = TellrawMessage.empty().then("Pet List: ").color("#d1c9c9");

                    for (PetType type : PetType.values()) {
                        if (type == PetType.UNKNOWN) continue;
                        Optional<IPetConfig> config = SimplePets.getPetConfigManager().getPetConfig(type);
                        String tooltip = "";
                        NamedTextColor color = NamedTextColor.GREEN;
                        if (config.isPresent() && (!config.get().isEnabled())) {
                            color = NamedTextColor.RED;
                            tooltip = "&cDISABLED";
                        } else if (!type.isSupported()) {
                            color = NamedTextColor.GOLD;
                            tooltip = "&6NOT SUPPORTED";
                        } else if (!SimplePets.getSpawnUtil().isRegistered(type)) {
                            color = NamedTextColor.YELLOW;
                            tooltip = "&eNOT REGISTERED";
                        } else if (type.isInDevelopment() && (!ConfigOption.PET_TOGGLES_DEV_MOBS.get())) {
                            color = NamedTextColor.GRAY;
                            tooltip = "&7IN DEVELOPMENT";
                        }
                        if (ConfigOption.MISC_TOGGLES_LIST_RESTRICTIONS.get()) {
                            if (color != NamedTextColor.GREEN) continue;
                            if (!Utilities.hasPermission(sender, type.getPermission())) continue;
                        }

                        raw.then(type.getName()).color(color);
                        if (!tooltip.isEmpty()) raw.tooltip(tooltip);
                        raw.then(", ").color("#d1c9c9");
                    }

                    raw.send(sender);
                });
    }
}
