package simplepets.brainsynder.api.plugin.utils;

import lib.brainsynder.ServerVersion;
import org.jetbrains.annotations.NotNull;
import simplepets.brainsynder.api.plugin.SimplePets;

import java.lang.reflect.Constructor;

public final class HelperUtilities {
    public static final String NMS_PATH = new String(new byte[]{
            115, 105, 109, 112, 108, 101, 112, 101, 116, 115, 46, 98, 114, 97, 105, 110, 115, 121, 110, 100, 101, 114, 46, 118, 101, 114, 115, 105, 111, 110, 115
    });

    public static <T> @NotNull T getVersionedClass(@NotNull String className, @NotNull Class<T> expectedType, @NotNull Class<? extends T> fallbackType) {
        String mcVersion = ServerVersion.getVersion().name();
        String path = NMS_PATH + "." + mcVersion + "." + className;

        // Try version-specific
        try {
            Class<?> rawType = Class.forName(path);

            if (!expectedType.isAssignableFrom(rawType)) {
                throw new IllegalStateException("Class " + path + " does not implement/extend " + expectedType.getName());
            }

            T instance = (T) newInstanceNoArgs(rawType);
            SimplePets.getPlugin().getLogger().info("Found support for version: " + mcVersion + " (" + path + ")");
            return instance;
        } catch (Throwable ex) {
            // Fallback
            try {
                T instance = newInstanceNoArgs(fallbackType);
                SimplePets.getPlugin().getLogger().warning("Missing version support for: " + mcVersion + " (" + path + "). Using fallback: " + fallbackType.getSimpleName());
                return instance;
            } catch (Throwable fallbackEx) {
                fallbackEx.addSuppressed(ex);
                throw new RuntimeException("Failed to create fallback for " + path, fallbackEx);
            }
        }
    }

    private static <T> @NotNull T newInstanceNoArgs(@NotNull Class<? extends T> type) throws Exception {
        Constructor<? extends T> constructor = type.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }
}
