package simplepets.brainsynder.api.wrappers;

public enum PufferState {
    SMALL,
    MEDIUM,
    LARGE;

    public static PufferState getByName(String name) {
        for (PufferState wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return SMALL;
    }
}