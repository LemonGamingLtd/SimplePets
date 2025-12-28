package simplepets.brainsynder.api.wrappers;

import org.bukkit.NamespacedKey;

import java.util.Locale;

public enum ArmadilloPhase {
    STANDING("9164ed0e0ef69b0ce7815e4300b4413a4828fcb0092918543545a418a48e0c3c", "IDLE"),
    ROLLED_UP("404d9164e8f76dcdde5f8d10ff67deb2d658a6008d23c44fb2f844e08591c01d", "ROLLING");

    private final NamespacedKey key;
    private final String texture;
    private final String mojangName;

    ArmadilloPhase(String texture, String mojangName) {
        this.texture = "http://textures.minecraft.net/texture/" + texture;
        this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
        this.mojangName = mojangName;
    }

    public String getMojangName() {
        return mojangName;
    }

    public String getTexture() {
        return texture;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public static ArmadilloPhase getByID(int id) {
        for (ArmadilloPhase phase : values()) if (phase.ordinal() == id) return phase;
        return STANDING;
    }

    public static ArmadilloPhase getByName(String name) {
        for (ArmadilloPhase phase : values()) {
            if (phase.name().equalsIgnoreCase(name)) return phase;
            if (phase.getMojangName().equalsIgnoreCase(name)) return phase;
        }
        return STANDING;
    }

    public static ArmadilloPhase getPrevious(ArmadilloPhase current) {
        if (current == STANDING) return ROLLED_UP;
        return values()[(current.ordinal() - 1)];
    }

    public static ArmadilloPhase getNext(ArmadilloPhase current) {
        if (current == ROLLED_UP) return STANDING;
        return values()[(current.ordinal() + 1)];
    }
}