package simplepets.brainsynder.utils;

public enum RenameType {
    COMMAND,
    CHAT,
    ANVIL,
    SIGN,
    DIALOG;

    public static RenameType getType(String name, RenameType fallback) {
        try {
            return valueOf(name.toUpperCase());
        } catch (Exception e) {
            return fallback;
        }
    }
}
