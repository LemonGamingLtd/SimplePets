package simplepets.brainsynder.api.wrappers.villager;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bukkit.Material;

/**
 * This is used to handle the 1.14 Villager Biome Types
 */
public enum BiomeType {
    DESERT(ItemBuilder.of(Material.SAND)),
    JUNGLE(ItemBuilder.of(Material.VINE)),
    PLAINS(ItemBuilder.of(Material.GRASS_BLOCK)),
    SAVANNA(ItemBuilder.of(Material.ACACIA_LOG)),
    SNOW(ItemBuilder.of(Material.SNOW_BLOCK)),
    SWAMP(ItemBuilder.of(Material.SLIME_BLOCK)),
    TAIGA(ItemBuilder.of(Material.PODZOL));

    private final ItemBuilder icon;

    BiomeType(ItemBuilder icon) {
        this.icon = icon;
    }

    public ItemBuilder getIcon() {
        return icon;
    }

    public static BiomeType getByName(String name) {
        for (BiomeType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return DESERT;
    }
}
