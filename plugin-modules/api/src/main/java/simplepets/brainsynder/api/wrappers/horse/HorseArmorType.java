package simplepets.brainsynder.api.wrappers.horse;

import org.bsdevelopment.pluginutils.version.VersionCompatibility;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemType;

public enum HorseArmorType {
    NONE(0, "barrier"),
    LEATHER(1, "leather_horse_armor"),
    @VersionLimit(min = {1, 21, 9}) COPPER(2, "copper_horse_armor"),
    IRON(3, "iron_horse_armor"),
    GOLD(4, "golden_horse_armor"),
    DIAMOND(5, "diamond_horse_armor"),
    @VersionLimit(min = {1, 21, 11}) NETHERITE(6, "netherite_horse_armor");

    private final int id;
    private final String rawMaterial;

    HorseArmorType(int id, String rawMaterial) {
        this.id = id;
        this.rawMaterial = rawMaterial;
    }

    public boolean isSupported() {
        try {
            return VersionCompatibility.isCompatible(getClass().getField(this.name()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return true;
    }

    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(rawMaterial);
    }

    public ItemType itemType() {
        return Registry.ITEM.get(getKey());
    }

    public static HorseArmorType getByName(String name) {
        for (HorseArmorType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return NONE;
    }

    public static HorseArmorType fromId(int id) {
        for (HorseArmorType armor : values()) {
            if (armor.getId() == id)
                return armor;
        }
        return null;
    }

    private static HorseArmorType traverse(HorseArmorType current, int step, HorseArmorType fallback) {
        int length = values().length;
        int start = current.ordinal();

        for (int i = 1; i <= length; i++) {
            int index = Math.floorMod(start + step * i, length);
            HorseArmorType candidate = values()[index];
            if (candidate.isSupported()) return candidate;
        }
        return fallback;
    }

    public static HorseArmorType getPrevious(HorseArmorType current) {
        return traverse(current, -1, current);
    }

    public static HorseArmorType getNext(HorseArmorType current) {
        return traverse(current, 1, NONE);
    }

    public int getId() {
        return this.id;
    }
}
