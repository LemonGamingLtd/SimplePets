package simplepets.brainsynder.api.wrappers;

public enum DyeColorWrapper {
    WHITE("#f9fffe"),
    ORANGE("#f9801d"),
    MAGENTA("#c74ebd"),
    LIGHT_BLUE("#3ab3da"),
    YELLOW("#fed83d"),
    LIME("#80c71f"),
    PINK("#f38baa"),
    GRAY("#474f52"),
    LIGHT_GRAY("#9d9d97"),
    CYAN("#169c9c"),
    PURPLE("#8932b8"),
    BLUE("#3c44aa"),
    BROWN("#835432"),
    GREEN("#5e7c16"),
    RED("#b02e26"),
    BLACK("#1d1d21");

    private final String hexColor;

    DyeColorWrapper(String hexColor) {
        this.hexColor = hexColor;
    }

    /**
     * Returns the dye numeric ID (0-15), matching Minecraft's DyeColor ordering in 1.21+.
     * Used for NMS collar color data and sheep color bytes.
     */
    public int getWoolData() {
        return ordinal();
    }

    /**
     * Returns the hex color string (e.g. {@code "#ff5555"}).
     */
    public String getHexColor() {
        return hexColor;
    }

    /**
     * Returns the color as a {@code &#RRGGBB} legacy hex code for use in item names/lore
     * processed by BSPluginUtils Colorize.
     */
    public String getChatColor() {
        return "&#" + hexColor.substring(1);
    }

    public static DyeColorWrapper getByName(String name) {
        for (DyeColorWrapper wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return WHITE;
    }

    public static DyeColorWrapper getPrevious(DyeColorWrapper current) {
        int original = current.ordinal();
        if (original == 0) return BLACK;
        return values()[original - 1];
    }

    public static DyeColorWrapper getNext(DyeColorWrapper current) {
        if (current.ordinal() == 15) return WHITE;
        return values()[current.ordinal() + 1];
    }

    public static DyeColorWrapper getByWoolData(byte data) {
        int id = data & 0xFF;
        if (id < values().length) return values()[id];
        return null;
    }
}
