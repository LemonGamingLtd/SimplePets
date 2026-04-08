package simplepets.brainsynder.api.plugin.utils;

import lib.brainsynder.nbt.*;
import lib.brainsynder.nbt.other.IStorageList;
import org.bsdevelopment.pluginutils.libs.json.JsonArray;
import org.bsdevelopment.pluginutils.libs.json.JsonObject;
import org.bsdevelopment.pluginutils.libs.json.JsonValue;
import org.bsdevelopment.pluginutils.version.ServerVersion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplepets.brainsynder.api.plugin.SimplePets;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class HelperUtilities {
    public static final String NMS_PATH = new String(new byte[]{
            115, 105, 109, 112, 108, 101, 112, 101, 116, 115, 46, 98, 114, 97, 105, 110, 115, 121, 110, 100, 101, 114, 46, 118, 101, 114, 115, 105, 111, 110, 115
    });

    public static @Nullable String resolveTargetVersion(@NotNull String className, @NotNull Map<ServerVersion, ServerVersion> versionLinks) {
        ServerVersion current = ServerVersion.getVersion();
        ClassLoader loader = HelperUtilities.class.getClassLoader();

        if (classExists(NMS_PATH + "." + current.getVersionName() + "." + className, loader)) return current.getVersionName();

        if (versionLinks.containsKey(current)) {
            String linked = versionLinks.get(current).getVersionName();
            if (classExists(NMS_PATH + "." + linked + "." + className, loader)) return linked;
        }

        ServerVersion currentVer = ServerVersion.getVersion();
        ServerVersion bestFallback = null;
        for (ServerVersion version : ServerVersion.getVersions()) {
            if (!classExists(NMS_PATH + "." + version.getVersionName() + "." + className, loader)) continue;
            if (!currentVer.isEqualOrNewer(version)) continue;
            if (bestFallback == null || version.isStrictlyNewer(bestFallback)) bestFallback = version;
        }
        return bestFallback != null ? bestFallback.getVersionName() : null;
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
        String mcVersion = ServerVersion.getVersion().getVersionName();
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

    public static JsonObject toJsonObject(StorageTagCompound compound) {
        JsonObject json = new JsonObject();
        compound.getKeySet().forEach(key -> {
            StorageBase base = compound.getTag(key);
            if (compound.isBoolean(key)) {
                json.add(key, compound.getBoolean(key));
            } else if (base.getId() >= 1 && base.getId() <= 6) {
                switch (base.getId()) {
                    case 1, 2, 3 -> json.add(key, compound.getInteger(key));
                    case 4 -> json.add(key, compound.getLong(key) + "l");
                    case 5 -> json.add(key, compound.getFloat(key) + "f");
                    case 6 -> json.add(key, compound.getDouble(key) + "d");
                }
            } else if (base instanceof IStorageList storageList) {
                JsonArray array = new JsonArray();
                Object list = storageList.getList();
                if (list instanceof byte[]) {
                    for (byte v : (byte[]) list) array.add(v + "b");
                } else if (list instanceof int[]) {
                    for (int v : (int[]) list) array.add(v);
                } else if (list instanceof long[]) {
                    for (long v : (long[]) list) array.add(v + "l");
                } else if (list instanceof List<?> l) {
                    l.forEach(item -> array.add(String.valueOf(item).replace("\"", "")));
                }
                json.add(key, array);
            } else if (base instanceof StorageTagCompound nested) {
                json.add(key, toJsonObject(nested));
            } else if (base instanceof StorageTagString string) {
                json.add(key, string.getString());
            }
        });
        return json;
    }

    public static StorageTagCompound fromJsonObject(JsonObject json) {
        StorageTagCompound compound = new StorageTagCompound();
        json.names().forEach(key -> {
            JsonValue value = json.get(key);
            if (value.isNumber()) {
                compound.setInteger(key, value.asInt());
            } else if (value.isBoolean()) {
                compound.setBoolean(key, value.asBoolean());
            } else if (value.isString()) {
                String s = value.asString();
                if (s.endsWith("f")) {
                    try { compound.setFloat(key, Float.parseFloat(s.substring(0, s.length() - 1))); }
                    catch (NumberFormatException e) { compound.setString(key, s); }
                } else if (s.endsWith("d")) {
                    try { compound.setDouble(key, Double.parseDouble(s.substring(0, s.length() - 1))); }
                    catch (NumberFormatException e) { compound.setString(key, s); }
                } else if (s.endsWith("l")) {
                    try { compound.setLong(key, Long.parseLong(s.substring(0, s.length() - 1))); }
                    catch (NumberFormatException e) { compound.setString(key, s); }
                } else {
                    compound.setString(key, s);
                }
            } else if (value.isArray()) {
                JsonArray array = value.asArray();
                List<Byte> bytes = new ArrayList<>();
                List<Integer> ints = new ArrayList<>();
                List<Long> longs = new ArrayList<>();
                StorageTagList list = new StorageTagList();
                array.values().forEach(v -> {
                    if (v.isString()) {
                        String s = v.asString();
                        if (s.endsWith("l")) {
                            try { longs.add(Long.parseLong(s.replace("l", ""))); }
                            catch (NumberFormatException e) { list.appendTag(new StorageTagString(s)); }
                        } else if (s.endsWith("b")) {
                            try { bytes.add(Byte.parseByte(s.replace("b", ""))); }
                            catch (NumberFormatException e) { list.appendTag(new StorageTagString(s)); }
                        } else {
                            try { ints.add(Integer.parseInt(s)); }
                            catch (NumberFormatException e) { list.appendTag(new StorageTagString(s)); }
                        }
                    } else if (v.isNumber()) {
                        ints.add(v.asInt());
                    }
                });
                if (!bytes.isEmpty()) compound.setTag(key, new StorageTagByteArray(bytes));
                else if (!ints.isEmpty()) compound.setTag(key, new StorageTagIntArray(ints));
                else if (!longs.isEmpty()) compound.setTag(key, new StorageTagLongArray(longs));
                else compound.setTag(key, list);
            } else if (value.isObject()) {
                compound.setTag(key, fromJsonObject(value.asObject()));
            }
        });
        return compound;
    }
}
