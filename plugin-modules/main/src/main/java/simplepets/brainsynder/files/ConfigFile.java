package simplepets.brainsynder.files;

import org.bsdevelopment.pluginutils.files.YamlFile;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.api.plugin.config.internal.ConfigEntry;
import simplepets.brainsynder.debug.DebugLevel;

public class ConfigFile extends YamlFile {
    public ConfigFile(PetCore core) {
        super(core.getDataFolder(), "config.yml");
    }

    @Override
    public void loadDefaults() {
        ConfigOption.REGISTRY.byPath().forEach((key, entry) -> {
            // Load the default values just in case
            if (entry.description() == null) {
                addDefault(key, entry.defaultValue());
            } else {
                addDefault(key, entry.defaultValue(), entry.description()
                    .replace("{default}", String.valueOf(entry.defaultValue()))
                );
            }

            // Moves all the old keys to the new key
            if (!entry.pastPaths().isEmpty()) {
                entry.pastPaths().forEach(oldKey -> {
                    String old = String.valueOf(oldKey);
                    if (contains(old)) {
                        set(key, get(old), false);
                        set(old, null, false);
                    }
                });
            }
        });

        updateSections();
    }

    public void initValues() {
        ConfigOption.REGISTRY.byPath().forEach((key, entry) -> {
            // Fetch the configs value
            Object value = get(key);

            // Validate the value, make sure the types match to prevent booleans becoming numbers
            if (value == null) {
                value = entry.defaultValue();
            } else if (!value.getClass().getSimpleName().equals(entry.defaultValue().getClass().getSimpleName())) {
                SimplePets.getDebugLogger().debug(DebugLevel.CRITICAL, "Value of '" + key + "' can not be a '" + value.getClass().getSimpleName() + "' must be a '" + entry.defaultValue().getClass().getSimpleName() + "'" + ((entry.example() != null) ? " Example(s): " + entry.example() : ""));
                value = entry.defaultValue();
            }
            // Store the configured value into the Entry
            ((ConfigEntry<Object>) entry).set(value, false);
        });

        ConfigOption.REGISTRY.setSaveHandler(entry -> {
            set(entry.path(), entry.valueToConfigValue(), false);
            save();
        });
    }


    public void setEnum(String key, Enum anEnum) {
        set(key, anEnum.name());
    }

    public <E extends Enum> E getEnum(String key, Class<E> type) {
        return getEnum(key, type, null);
    }

    public <E extends Enum> E getEnum(String key, Class<E> type, E fallback) {
        if (!contains(key)) return fallback;
        return (E) E.valueOf(type, getString(key));
    }

    private void updateSections() {
        remove("PetItemStorage.Enable");
        remove("PetItemStorage.Inventory-Size");
        remove("WorldGuard.BypassPermission");
        remove("PlotSquared.BypassPermission");
        remove("PlotSquared.On-Unclaimed-Plots.Move");
        remove("PlotSquared.On-Unclaimed-Plots.Spawn");
        remove("PlotSquared.On-Unclaimed-Plots.Riding");
        remove("PlotSquared.On-Roads.Move");
        remove("PlotSquared.On-Roads.Spawn");
        remove("PlotSquared.On-Roads.Riding");
        remove("PlotSquared.Block-If-Denied.Move");
        remove("PlotSquared.Block-If-Denied.Spawn");
        remove("PlotSquared.Block-If-Denied.Riding");
        remove("WorldBorder.Block-If-Denied.Move");
        remove("WorldBorder.Block-If-Denied.Spawn");
        remove("WorldBorder.Block-If-Denied.Riding");
        move("Needs-Pet-Permission-To-Open-GUI", "Permissions.Needs-Pet-Permission-for-GUI");
        move("Needs-Data-Permissions", "Permissions.Data-Permissions");
        move("Needs-Permission", "Permissions.Enabled");
        move("Remove-Item-If-No-Permission", "Permissions.Only-Show-Pets-Player-Can-Access");
        remove("WorldGuard.Spawning.Always-Allowed");
        remove("WorldGuard.Spawning.Blocked-Regions");
        remove("WorldGuard.Pet-Entering.Always-Allowed");
        remove("WorldGuard.Pet-Entering.Blocked-Regions");
        remove("WorldGuard.Pet-Riding.Always-Allowed");
        remove("WorldGuard.Pet-Riding.Blocked-Regions");
        remove("OldPetRegistering");
        remove("MySQL.Options.Auto_Reconnect");
        remove("PetToggles.Weight.Enabled");
        remove("PetToggles.Weight.Weight_Stacked");
        remove("PetToggles.Weight.Max_Weight");
        remove("Complete-Mobspawning-Deny-Bypass");
    }
}
