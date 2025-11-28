package simplepets.brainsynder.api.wrappers;

public enum FoxType {
    RED,
    WHITE;

    public static FoxType getByName(String name) {
        for (FoxType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return RED;
    }
}
