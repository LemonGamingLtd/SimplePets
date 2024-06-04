package simplepets.brainsynder.api.wrappers;

import org.bukkit.NamespacedKey;

import java.util.Locale;

public enum WolfType {
    PALE ("72ce161e3205d89e7e4d3ec04d25abfea6231a2277a2bd76f4693f4ce6189a2d"),
    SPOTTED ("bbd23b4ceac1258132bcb7be63dee7cb0ce269a48640e72d4f9c2d4af74af670"),
    SNOWY ("50b8e8a15dff16be494c687f13793ee35ac992a2cacff25f327bd240416851f2"),
    BLACK ("f6976503340eb030ab77f300a2efa12f2179df2ca9ca0d292d983c50ed933309"),
    ASHEN("470608d436f5aa213047b54a443a091dd2d71cbcf6e73338b2352ce11251dff1"),
    RUSTY ("ee03c8f1df96a7a30e118c35e9d7174f1d9a6495d4d88693ad0eeee3e73423c0"),
    WOODS ("6f2908155700d377a678362efbd680160316cc2bc1ffd35849cc9d96637abb65"),
    CHESTNUT ("762ad8c70badb7874a635920b2f934f5151eff743b8e51e8ba7baf3a3f31a256"),
    STRIPED ("b3f2de525328e0a7162b40fba2a5bc6aa4deebbdd6996114691ce1d7ef537ccf");

    private final NamespacedKey key;
    private final String texture;
    WolfType(String texture) {
        this.texture = "http://textures.minecraft.net/texture/"+texture;
        this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
    }

    public String getTexture() {
        return texture;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public static WolfType getByID(int id) {
        for (WolfType wolfType : values()) if (wolfType.ordinal() == id) return wolfType;
        return PALE;
    }
    public static WolfType getByName(String name) {
        for (WolfType wolfType : values()) if (wolfType.name().equalsIgnoreCase(name)) return wolfType;
        return PALE;
    }

    public static WolfType getPrevious(WolfType current) {
        if (current == PALE) return STRIPED;
        return values()[(current.ordinal() - 1)];
    }
    public static WolfType getNext(WolfType current) {
        if (current == STRIPED) return PALE;
        return values()[(current.ordinal() + 1)];
    }
}