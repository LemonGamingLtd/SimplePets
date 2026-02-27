package simplepets.brainsynder.api.wrappers;

public enum FoxVariant {
    RED,
    WHITE;

    public static FoxVariant getByID(int id) {
        for (FoxVariant v : values()) {
            if (v.ordinal() == id) {
                return v;
            }
        }
        return RED;
    }

    public static FoxVariant getByName(String name) {
        for (FoxVariant wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return RED;
    }

    public static FoxVariant getPrevious(FoxVariant current) {
        if (current == RED) return WHITE;
        return values()[(current.ordinal() - 1)];
    }

    public static FoxVariant getNext(FoxVariant current) {
        if (current == WHITE) return RED;
        return values()[(current.ordinal() + 1)];
    }
}
