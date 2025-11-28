package simplepets.brainsynder.api.wrappers;

public enum AngerLevel {
    CALM,
    AGITATED,
    ANGRY;

    public static AngerLevel getByName(String name) {
        for (AngerLevel wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return CALM;
    }
}