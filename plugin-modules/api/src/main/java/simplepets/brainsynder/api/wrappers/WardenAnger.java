package simplepets.brainsynder.api.wrappers;

public enum WardenAnger {
    CALM,
    AGITATED,
    ANGRY;

    public static WardenAnger getByID(int id) {
        for (WardenAnger v : values()) {
            if (v.ordinal() == id) {
                return v;
            }
        }
        return CALM;
    }

    public static WardenAnger getByName(String name) {
        for (WardenAnger wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return CALM;
    }

    public static WardenAnger getPrevious(WardenAnger current) {
        return switch (current) {
            case CALM -> ANGRY;
            case AGITATED -> CALM;
            case ANGRY -> AGITATED;
        };
    }

    public static WardenAnger getNext(WardenAnger current) {
        return switch (current) {
            case CALM -> AGITATED;
            case AGITATED -> ANGRY;
            case ANGRY -> CALM;
        };
    }
}