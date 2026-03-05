package simplepets.brainsynder.files;

import lib.brainsynder.files.YamlFile;
import lib.brainsynder.utils.Colorize;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.api.plugin.config.internal.ConfigEntry;

import java.io.File;

public class MessageFile extends YamlFile {
    public MessageFile(File folder) {
        super(folder, "messages.yml");
    }

    @Override
    public void loadDefaults() {
        addSectionHeader(MessageOption.PREFIX.path(), "NOTICE: All the messages in this file can be customized with color codes\nThat includes the HEX color codes added in 1.16\nExample HEX color: &#ff0000 = RED");
        MessageOption.REGISTRY.byPath().forEach((key, entry) -> {
            if (entry.description() == null) {
                addDefault(key, entry.defaultValue());
            } else {
                addDefault(key, entry.defaultValue(), entry.description());
            }
        });
    }

    public void initValues() {
        MessageOption.REGISTRY.byPath().forEach((key, entry) -> {
            Object value = get(key);
            if (value == null) return;
            ((ConfigEntry<Object>) entry).set(value, false);
        });

        MessageOption.REGISTRY.setSaveHandler(entry -> {
            set(entry.path(), entry.valueToConfigValue());
            try {
                save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public String getTranslation(ConfigEntry<String> option) {
        return getTranslation(option, true);
    }

    public String getTranslation(ConfigEntry<String> option, boolean translateColor) {
        String message = option.get();
        if (message.contains("{prefix}") && (option != MessageOption.PREFIX))
            message = message.replace("{prefix}", MessageOption.PREFIX.get());
        return translateColor ? Colorize.translateBungeeHex(message) : message;
    }
}
