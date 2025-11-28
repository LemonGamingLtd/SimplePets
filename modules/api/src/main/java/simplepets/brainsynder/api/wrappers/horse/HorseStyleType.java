package simplepets.brainsynder.api.wrappers.horse;

import org.bukkit.entity.Horse;

public enum HorseStyleType {
    NONE(Horse.Style.NONE),
    WHITE(Horse.Style.WHITE),
    WHITEFIELD( Horse.Style.WHITEFIELD),
    WHITE_DOTS(Horse.Style.WHITE_DOTS),
    BLACK_DOTS(Horse.Style.BLACK_DOTS);

    private final Horse.Style bukkitStyle;

    HorseStyleType(Horse.Style bukkitStyle) {
        this.bukkitStyle = bukkitStyle;
    }

    public static HorseStyleType getByName(String name) {
        for (HorseStyleType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return NONE;
    }

    public Horse.Style getBukkitStyle() {
        return this.bukkitStyle;
    }
}