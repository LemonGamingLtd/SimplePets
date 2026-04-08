package simplepets.brainsynder.api.plugin.config.internal;

import org.bsdevelopment.pluginutils.text.Colorize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ConfigEntry<T> {
    private final ConfigRegistry registry;
    private final String path;
    private final String description;
    private final T defaultValue;

    private final List<String> pastPaths;
    private volatile T value;

    ConfigEntry(ConfigRegistry registry, String path, T defaultValue, String description) {
        this.registry = Objects.requireNonNull(registry, "registry");
        this.path = Objects.requireNonNull(path, "path");
        this.defaultValue = Objects.requireNonNull(defaultValue, "defaultValue");
        this.description = description;
        this.pastPaths = new ArrayList<>();
        this.value = defaultValue;
    }

    ConfigEntry(ConfigRegistry registry, String path, T defaultValue) {
        this(registry, path, defaultValue, null);
    }

    public String path() {
        return path;
    }

    public T defaultValue() {
        return defaultValue;
    }

    public String description() {
        return description;
    }

    public List<String> pastPaths() {
        return Collections.unmodifiableList(pastPaths);
    }

    public ConfigEntry<T> pastPaths(String... paths) {
        if (paths == null || paths.length == 0) return this;
        Collections.addAll(this.pastPaths, paths);
        return this;
    }

    public T get() {
        return value;
    }

    public String getColorized() {
        return Colorize.translateBungeeHex(String.valueOf(value));
    }

    public Object defaultToConfigValue() {
        return serialize(defaultValue);
    }

    public Object valueToConfigValue() {
        return serialize(value);
    }

    public String defaultForComment() {
        Object v = defaultToConfigValue();
        return (v == null) ? "null" : String.valueOf(v);
    }

    private Object serialize(T value) {
        if (value == null) return null;

        @SuppressWarnings("unchecked")
        Class<T> type = (Class<T>) defaultValue.getClass();
        ConfigSerializer<T> serializer = registry.serializerOrNull(type);

        return (serializer == null) ? value : serializer.serialize(value);
    }

    public void set(T value, boolean saveConfig) {
        this.value = (value == null) ? defaultValue : value;
        if (!saveConfig) return;
        registry.save(this);
    }

    public void set(T value) {
        set(value, true);
    }

    public String example() {
        Object dv = defaultValue;
        if (dv instanceof Boolean) return "true or false";
        if (dv instanceof Double || dv instanceof Float) return "1.0";
        if (dv instanceof Integer || dv instanceof Long) return "1, 2, 3";
        if (dv instanceof String) return "\"im a string\"";
        if (dv instanceof List<?>) return "[]";
        return null;
    }
}
