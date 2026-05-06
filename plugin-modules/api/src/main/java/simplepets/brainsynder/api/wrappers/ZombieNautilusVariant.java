package simplepets.brainsynder.api.wrappers;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bukkit.NamespacedKey;

import java.util.Locale;

public enum ZombieNautilusVariant implements Iconable {
    TEMPERATE ("fd9a933376da44c3391307cb9f4cf03f16f3a54f495fd5a11bad8a373f9d5720"),
    WARM ("d516e18f400b8c48190b3438a75feefda45367f0ae5d4e49732c417251650ecf");

    private final String texture;

    ZombieNautilusVariant(String texture) {
        this.texture = "http://textures.minecraft.net/texture/" + texture;
    }

    public NamespacedKey getKey() {
        return NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));
    }

    @Override
    public ItemBuilder getIcon() {
        return ItemBuilder.playerSkull(texture);
    }

    public static ZombieNautilusVariant getByName(String name) {
        for (ZombieNautilusVariant variant : values()) {
            if (variant.name().equalsIgnoreCase(name)) return variant;
        }
        return TEMPERATE;
    }

    public static ZombieNautilusVariant getNext(ZombieNautilusVariant current) {
        return (current == TEMPERATE) ? WARM : TEMPERATE;
    }
}
