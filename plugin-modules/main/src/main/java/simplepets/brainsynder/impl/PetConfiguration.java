package simplepets.brainsynder.impl;

import com.google.common.collect.Lists;
import lib.brainsynder.apache.WordUtils;
import lib.brainsynder.files.JsonFile;
import lib.brainsynder.item.ItemBuilder;
import lib.brainsynder.json.JsonArray;
import lib.brainsynder.json.JsonObject;
import lib.brainsynder.nbt.StorageTagTools;
import lib.brainsynder.sounds.SoundMaker;
import lib.brainsynder.utils.Capitalise;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.pet.*;
import simplepets.brainsynder.api.pet.annotations.DisableDefault;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.debug.DebugLevel;
import simplepets.brainsynder.utils.Keys;
import simplepets.brainsynder.utils.Utilities;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PetConfiguration implements PetConfigManager {
    private final PetCore plugin;
    private final Map<PetType, IPetConfig> configMap;

    public PetConfiguration(PetCore plugin) {
        this.plugin = plugin;
        this.configMap = new HashMap<>();

        for (PetType type : PetType.values()) {
            if (!type.isSupported()) continue;
            if (!SimplePets.getSpawnUtil().isRegistered(type)) continue;
            configMap.put(type, new PetConfig(type));
        }
    }

    public void reset() {
        configMap.clear();

        for (PetType type : PetType.values()) {
            if (!type.isSupported()) continue;
            if (!SimplePets.getSpawnUtil().isRegistered(type)) continue;
            configMap.put(type, new PetConfig(type));
        }
    }

    public void reset(PetType type) {
        configMap.remove(type);

        if (!type.isSupported()) return;
        if (!SimplePets.getSpawnUtil().isRegistered(type)) return;
        configMap.put(type, new PetConfig(type));
    }

    @Override
    public Optional<IPetConfig> getPetConfig(PetType type) {
        if (configMap.containsKey(type)) return Optional.of(configMap.get(type));
        return Optional.empty();
    }

    public class PetConfig implements IPetConfig {
        private final PetType type;
        private final JsonFile JSON;
        private final Map<String, JsonObject> additional;
        private final Map<CommandReason, List<String>> commandMap;

        private PetConfig(PetType type) {
            this.type = type;
            this.additional = new HashMap<>();
            this.commandMap = new HashMap<>();

            this.JSON = new JsonFile(new File(new File(plugin.getDataFolder() + File.separator + "Pets"), type.getName() + ".json"), true) {
                @Override
                public void loadDefaults() {
                    setDefault("enabled", true);
                    setDefault("hat", true);
                    setDefault("mount", true);
                    type.getCustomization().ifPresent(customization -> setDefault("ambient-sound", customization.ambient().name()));

                    JsonObject reasons = new JsonObject();
                    for (CommandReason reason : CommandReason.values()) reasons.add(reason.name(), new JsonArray());
                    setDefault("commands", reasons);

                    setDefault("ride_speed", ConfigOption.INSTANCE.PET_TOGGLES_RIDE_SPEED.getValue());
                    setDefault("walk_speed", ConfigOption.INSTANCE.PET_TOGGLES_WALK_SPEED.getValue());
                    setDefault("fly_speed", ConfigOption.INSTANCE.PET_TOGGLES_FLY_SPEED.getValue());
                    setDefault("fly", canFlyDefault(type));
                    setDefault("float_down", false);

                    setDefault("display_name", "&a&l%player%'s " + Capitalise.capitalize(type.getName().replace("_", " ")) + " Pet");
                    setDefault("item", StorageTagTools.toJsonObject(type.getBuilder().toCompound()));

                    JsonObject dataObject = new JsonObject();
                    type.getPetData().forEach(petData -> {
                        JsonObject data = new JsonObject();
                        data.set("enabled", !petData.getClass().isAnnotationPresent(DisableDefault.class));

                        setJsonDefaultValue(data, "default", petData.getDefaultValue());

                        JsonObject values = new JsonObject();
                        petData.getDefaultItems().forEach((value, item) -> {
                            String name = petData.getNamespace().namespace();
                            name = name.replace("_", " ");
                            name = WordUtils.capitalize(name);

                            ItemBuilder builder = (ItemBuilder) item;
                            String raw = builder.getName();
                            raw = raw.replace("{name}", name);
                            builder.withName(raw);

                            values.add(String.valueOf(value), StorageTagTools.toJsonObject(builder.toCompound()));
                        });
                        data.set("values", values);

                        dataObject.add(petData.getNamespace().namespace(), data);
                    });
                    setDefault("data", dataObject);
                }
            };

            if (JSON.hasKey("commands")) {
                JsonObject commands = (JsonObject) JSON.getValue("commands");
                commands.names().forEach(s -> CommandReason.getReason(s).ifPresent(reason -> {
                    List<String> list = commandMap.getOrDefault(reason, Lists.newArrayList());
                    JsonArray array = (JsonArray) commands.get(s);
                    array.forEach(jsonValue -> list.add(jsonValue.asString()));
                    commandMap.put(reason, list);
                }));
            }

            // Ensure PetData sections are present and valid.
            type.getPetData().forEach(this::checkPetData);
        }

        public JsonFile getJSON() {
            return JSON;
        }

        @Override
        public void handleAdditionalStorage(String pluginKey, Function<JsonObject, JsonObject> json) {
            additional.put(pluginKey, json.apply(additional.getOrDefault(pluginKey, new JsonObject())));
        }

        @Override
        public String getDisplayName() {
            return JSON.getString("display_name");
        }

        @Override
        public boolean canHat(Player player) {
            if (ConfigOption.INSTANCE.PET_TOGGLES_HAT.getValue()) return true;
            if (JSON.getBoolean("hat", true)) return Utilities.hasPermission(player, type.getPermission("hat"));
            return false;
        }

        @Override
        public boolean canMount(Player player) {
            if (ConfigOption.INSTANCE.PET_TOGGLES_MOUNTABLE.getValue()) return true;
            if (JSON.getBoolean("mount", true)) return Utilities.hasPermission(player, type.getPermission("mount"));
            return false;
        }

        @Override
        public boolean canFly(Player player) {
            if (ConfigOption.INSTANCE.PET_TOGGLES_FLYABLE.getValue()) return true;
            if (JSON.getBoolean("fly", true)) return Utilities.hasPermission(player, type.getPermission("fly"));
            return false;
        }

        @Override
        public boolean isEnabled() {
            return JSON.getBoolean("enabled");
        }

        @Override
        public boolean canFloat() {
            return JSON.getBoolean("float_down", false);
        }

        @Override
        public double getRideSpeed() {
            return JSON.getDouble("ride_speed", ConfigOption.INSTANCE.PET_TOGGLES_RIDE_SPEED.getValue());
        }

        @Override
        public double getWalkSpeed() {
            return JSON.getDouble("walk_speed", ConfigOption.INSTANCE.PET_TOGGLES_WALK_SPEED.getValue());
        }

        @Override
        public double getFlySpeed() {
            return JSON.getDouble("fly_speed", ConfigOption.INSTANCE.PET_TOGGLES_FLY_SPEED.getValue());
        }

        @Override
        public Optional<EntityType> getEntityType() {
            return Optional.empty();
        }

        @Override
        public SoundMaker getSound() {
            if (!JSON.containsKey("ambient-sound")) return null;
            String sound = JSON.getString("ambient-sound", null);
            if (sound == null) return null;
            return SoundMaker.fromString(sound);
        }

        @Override
        public ItemBuilder getBuilder() {
            try {
                return ItemBuilder.fromCompound(StorageTagTools.fromJsonObject((JsonObject) JSON.getValue("item"))).handleMeta(ItemMeta.class, itemMeta -> {
                    itemMeta.getPersistentDataContainer().set(Keys.GUI_ITEM, PersistentDataType.INTEGER, 1);
                    itemMeta.getPersistentDataContainer().set(Keys.PET_TYPE_ITEM, PersistentDataType.STRING, type.getName());
                    return itemMeta;
                });
            } catch (Exception e) {
                SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Failed to get default item for '" + type.getName() + "'");
                SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Error: " + e.getMessage());
                return type.getBuilder();
            }
        }

        @Override
        public Optional<ItemBuilder> getDataItem(String namespace, Object value) {
            return getDataItem(namespace, value, null, false);
        }

        @Override
        public Optional<ItemBuilder> getDataItem(String namespace, Object value, ItemBuilder fallback) {
            return getDataItem(namespace, value, fallback, true);
        }

        private Optional<ItemBuilder> getDataItem(String namespace, Object value, ItemBuilder fallback, boolean allowInsertFallback) {
            if (!JSON.containsKey("data")) return Optional.empty();

            JsonObject dataRoot = (JsonObject) JSON.getValue("data");
            if (!dataRoot.names().contains(namespace)) return Optional.empty();

            JsonObject dataSection = (JsonObject) dataRoot.get(namespace);
            JsonObject values = ensureValuesObject(dataRoot, namespace, dataSection, true);

            String key = String.valueOf(value);

            if (values.names().contains(key)) {
                return Optional.of(ItemBuilder.fromCompound(StorageTagTools.fromJsonObject((JsonObject) values.get(key))));
            }

            if (fallback == null) return Optional.empty();

            if (allowInsertFallback) {
                values.add(key, StorageTagTools.toJsonObject(fallback.toCompound()));
                dataSection.set("values", values);
                dataRoot.add(namespace, dataSection);
                JSON.set("data", dataRoot);
                JSON.save(true);
                return Optional.of(fallback);
            }

            return Optional.of(fallback);
        }

        @Override
        public JsonObject getRawData(String namespace) {
            JsonObject data = new JsonObject();
            if (JSON.containsKey("data")) {
                JsonObject dataObject = (JsonObject) JSON.getValue("data");
                if (dataObject.names().contains(namespace)) data = (JsonObject) dataObject.get(namespace);
            }
            return data;
        }

        @Override
        public Map<CommandReason, List<String>> getCommands() {
            return commandMap;
        }

        private boolean checkPetData(PetData petData) {
            String namespace = petData.getNamespace().namespace();

            JsonObject dataRoot = ensureDataRoot(false);
            JsonObject section = dataRoot.names().contains(namespace) ? (JsonObject) dataRoot.get(namespace) : new JsonObject();

            boolean changed = false;

            boolean expectedEnabledDefault = !petData.getClass().isAnnotationPresent(DisableDefault.class);
            if (!section.names().contains("enabled")) {
                section.set("enabled", expectedEnabledDefault);
                changed = true;
            }

            if (!section.names().contains("default")) {
                setJsonDefaultValue(section, "default", petData.getDefaultValue());
                changed = true;
            }

            JsonObject values = section.names().contains("values") ? (JsonObject) section.get("values") : new JsonObject();
            if (values.names().isEmpty()) {
                petData.getDefaultItems().forEach((val, item) -> values.add(String.valueOf(val), StorageTagTools.toJsonObject(((ItemBuilder) item).toCompound())));
                changed = true;
            } else {
                for (Object object : petData.getDefaultItems().entrySet()) {
                    Map.Entry<String, ItemBuilder> entry = (Map.Entry<String, ItemBuilder>) object;

                    String key = String.valueOf(entry.getKey());
                    if (!values.names().contains(key)) {
                        values.add(key, StorageTagTools.toJsonObject(entry.getValue().toCompound()));
                        changed = true;
                    }
                }
            }

            section.set("values", values);
            dataRoot.add(namespace, section);

            if (changed) {
                JSON.set("data", dataRoot);
                JSON.save(true);
                return false;
            }
            return true;
        }

        private JsonObject ensureDataRoot(boolean save) {
            if (!JSON.containsKey("data")) {
                SimplePets.getDebugLogger().debug(DebugLevel.DEBUG, type.getName() + " | Missing 'data' section");
                JsonObject root = new JsonObject();
                JSON.set("data", root);
                if (save) JSON.save(true);
                return root;
            }
            return (JsonObject) JSON.getValue("data");
        }

        private JsonObject ensureValuesObject(JsonObject dataRoot, String namespace, JsonObject section, boolean save) {
            JsonObject values = section.names().contains("values") ? (JsonObject) section.get("values") : new JsonObject();
            if (values.names().isEmpty()) {
                for (PetData petData : type.getPetData()) {
                    if (!petData.getNamespace().namespace().equals(namespace)) continue;

                    petData.getDefaultItems().forEach((val, item) -> values.add(String.valueOf(val), StorageTagTools.toJsonObject(((ItemBuilder) item).toCompound())));

                    if (!section.names().contains("default")) {
                        setJsonDefaultValue(section, "default", petData.getDefaultValue());
                    }
                    if (!section.names().contains("enabled")) {
                        section.set("enabled", !petData.getClass().isAnnotationPresent(DisableDefault.class));
                    }
                    break;
                }

                section.set("values", values);
                dataRoot.add(namespace, section);
                JSON.set("data", dataRoot);
                if (save) JSON.save(true);
            }
            return values;
        }

        private void setJsonDefaultValue(JsonObject obj, String key, Object value) {
            if (value instanceof Integer i) {
                obj.set(key, i);
                return;
            }
            if (value instanceof Boolean b) {
                obj.set(key, b);
                return;
            }
            if (value instanceof Double d) {
                obj.set(key, d);
                return;
            }
            if (value instanceof Float f) {
                obj.set(key, f.doubleValue());
                return;
            }
            obj.set(key, String.valueOf(value));
        }

        private boolean canFlyDefault(PetType type) {
            return (type == PetType.BAT)
                || (type == PetType.BEE)
                || (type == PetType.BLAZE)
                || (type == PetType.PHANTOM)
                || (type == PetType.PARROT)
                || (type == PetType.GHAST)
                || (type == PetType.VEX)
                || (type == PetType.WITHER);
        }
    }
}
