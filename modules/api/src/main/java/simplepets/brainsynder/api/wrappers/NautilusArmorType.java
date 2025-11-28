package simplepets.brainsynder.api.wrappers;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemType;

public enum NautilusArmorType {
    NONE("barrier"),
    COPPER("copper_nautilus_armor"),
    IRON("iron_nautilus_armor"),
    GOLD("golden_nautilus_armor"),
    DIAMOND("diamond_nautilus_armor"),
    NETHERITE("netherite_nautilus_armor");

    private final ItemType itemType;

    NautilusArmorType(String rawMaterial) {
        this.itemType = Registry.ITEM.get(NamespacedKey.minecraft(rawMaterial));
    }

    public ItemType itemType() {
        return itemType;
    }

    public static NautilusArmorType getByName(String name) {
        for (NautilusArmorType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return NONE;
    }
}
