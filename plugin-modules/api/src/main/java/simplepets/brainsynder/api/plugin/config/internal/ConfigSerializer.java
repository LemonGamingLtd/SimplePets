package simplepets.brainsynder.api.plugin.config.internal;

@FunctionalInterface
public interface ConfigSerializer<T> {
    T deserialize(String raw);

    default String serialize(T value) {
        return String.valueOf(value);
    }
}