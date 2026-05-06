package simplepets.brainsynder.api.plugin.utils;

import org.bsdevelopment.pluginutils.version.ServerVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplepets.brainsynder.api.plugin.SimplePets;

import java.lang.reflect.Constructor;

public final class HelperUtilities {
    public static final String NMS_PATH = new String(new byte[]{
            115, 105, 109, 112, 108, 101, 112, 101, 116, 115, 46, 98, 114, 97, 105, 110, 115, 121, 110, 100, 101, 114, 46, 118, 101, 114, 115, 105, 111, 110, 115
    });

    public static @Nullable String resolveTargetVersion(@NotNull String className) {
        ServerVersion current = ServerVersion.getVersion();
        ClassLoader loader = HelperUtilities.class.getClassLoader();
        if (classExists(NMS_PATH + "." + current.getVersionName() + "." + className, loader)) return current.getVersionName();

        // Only supports for 26.1+ for version linking
        if (current.getVersionNumbers().getLeft() < 26) return null;

        ServerVersion bestFallback = null;
        for (ServerVersion version : ServerVersion.getVersions()) {
            if (!sameMajorMinor(current, version)) continue;
            if (!classExists(NMS_PATH + "." + version.getVersionName() + "." + className, loader)) continue;
            if (!current.isEqualOrNewer(version)) continue;
            if (bestFallback == null || version.isStrictlyNewer(bestFallback)) bestFallback = version;
        }
        return bestFallback != null ? bestFallback.getVersionName() : null;
    }

    private static boolean sameMajorMinor(@NotNull ServerVersion current, @NotNull ServerVersion target) {
        var currentVersionNumbers = current.getVersionNumbers();
        var targetVersionNumbers = target.getVersionNumbers();
        return currentVersionNumbers.getLeft().equals(targetVersionNumbers.getLeft()) && currentVersionNumbers.getMiddle().equals(targetVersionNumbers.getMiddle());
    }

    private static boolean classExists(@NotNull String className, @NotNull ClassLoader loader) {
        try {
            Class.forName(className, false, loader);
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    public static <T> @NotNull T getVersionedClass(@NotNull String className, @NotNull Class<T> expectedType, @NotNull Class<? extends T> fallbackType) {
        String currentVersion = ServerVersion.getVersion().getVersionName();
        String resolvedVersion = resolveTargetVersion(className);

        if (resolvedVersion != null) {
            String path = NMS_PATH + "." + resolvedVersion + "." + className;
            try {
                Class<?> rawType = Class.forName(path);

                if (!expectedType.isAssignableFrom(rawType)) {
                    throw new IllegalStateException("Class " + path + " does not implement/extend " + expectedType.getName());
                }

                T instance = (T) newInstanceNoArgs(rawType);
                if (resolvedVersion.equals(currentVersion)) {
                    SimplePets.getPlugin().getLogger().info("Found support for version: " + currentVersion);
                } else {
                    SimplePets.getPlugin().getLogger().info("Version " + currentVersion + " linked to " + resolvedVersion);
                }
                return instance;
            } catch (Throwable ex) {
                // Fall through to fallback
            }
        }

        try {
            T instance = newInstanceNoArgs(fallbackType);
            SimplePets.getPlugin().getLogger().warning("Missing version support for: " + currentVersion + ". Using fallback: " + fallbackType.getSimpleName());
            return instance;
        } catch (Throwable fallbackEx) {
            throw new RuntimeException("Failed to create fallback for " + className + " (" + currentVersion + ")", fallbackEx);
        }
    }

    private static <T> @NotNull T newInstanceNoArgs(@NotNull Class<? extends T> type) throws Exception {
        Constructor<? extends T> constructor = type.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

}
