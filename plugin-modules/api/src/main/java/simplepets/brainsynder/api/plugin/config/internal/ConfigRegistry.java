package simplepets.brainsynder.api.plugin.config.internal;

import org.bsdevelopment.pluginutils.sound.SafeSound;

import java.util.*;
import java.util.function.Consumer;


public class ConfigRegistry {
    private final Map<String, ConfigEntry<?>> entries = new LinkedHashMap<>();
    private final Map<Class<?>, ConfigSerializer<?>> serializers = new LinkedHashMap<>();
    private Consumer<ConfigEntry<?>> saveHandler = entry -> {};

    public void setSaveHandler(Consumer<ConfigEntry<?>> handler) {
        this.saveHandler = Objects.requireNonNull(handler, "handler");
    }

    void save(ConfigEntry<?> entry) {
        saveHandler.accept(entry);
    }

    public ConfigRegistry () {
        serializers.put(SafeSound.class, new ConfigSerializer<SafeSound>() {
            @Override
            public SafeSound deserialize(String raw) {
                return SafeSound.of(raw, "entity.generic.explode");
            }

            @Override
            public String serialize(SafeSound value) {
                return value.primaryName();
            }
        });
    }

    public <T> ConfigEntry<T> register(String path, T defaultValue, String description) {
        Objects.requireNonNull(path, "path");
        Objects.requireNonNull(defaultValue, "defaultValue");

        if (entries.containsKey(path)) {
            throw new IllegalStateException("Duplicate config path registered: " + path);
        }

        ConfigEntry<T> entry = new ConfigEntry<>(this, path, defaultValue, description);
        entries.put(path, entry);
        return entry;
    }

    public <T> ConfigEntry<T> register(String path, T defaultValue) {
        return register(path, defaultValue, null);
    }

    public <T> ConfigSerializer<T> register(Class<T> type, ConfigSerializer<T> serializer) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(serializer, "serializer");

        if (serializers.putIfAbsent(type, serializer) != null) {
            throw new IllegalStateException("Duplicate serializer registered for: " + type.getName());
        }

        return serializer;
    }

    @SuppressWarnings("unchecked")
    <T> ConfigSerializer<T> serializerOrNull(Class<T> type) {
        return (ConfigSerializer<T>) serializers.get(type);
    }

    public Collection<ConfigEntry<?>> all() {
        return Collections.unmodifiableCollection(entries.values());
    }

    public Map<String, ConfigEntry<?>> byPath() {
        return Collections.unmodifiableMap(entries);
    }

    public Optional<ConfigEntry<?>> find(String path) {
        return Optional.ofNullable(entries.get(path));
    }
}
