package simplepets.brainsynder.api.wrappers.horse;

import org.bsdevelopment.pluginutils.version.VersionCompatibility;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemType;

public enum HorseArmorType {
    NONE("barrier"),
    LEATHER("leather_horse_armor"),
    @VersionLimit(min = {1, 21, 9}) COPPER("copper_horse_armor"),
    IRON("iron_horse_armor"),
    GOLD("golden_horse_armor"),
    DIAMOND("diamond_horse_armor"),
    @VersionLimit(min = {1, 21, 11}) NETHERITE( "netherite_horse_armor");

    private final NamespacedKey key;
    private final ItemType itemType;

    HorseArmorType(String rawMaterial) {
        this.key = NamespacedKey.minecraft(rawMaterial);
        this.itemType = Registry.ITEM.get(key);
    }

    public boolean isSupported() {
        return itemType != null && VersionCompatibility.isCompatible(this);
    }

    public static HorseArmorType getByName(String name) {
        for (HorseArmorType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return NONE;
    }

    public NamespacedKey getKey() {
        return this.key;
    }

    public ItemType itemType() {
        return this.itemType;
    }
}
