package simplepets.brainsynder.sql;

import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.debug.DebugLevel;

public class SQLData {
    public static final String TABLE_PREFIX;
    public static final String DATABASE_NAME;
    public static boolean USE_SQLITE = false;

    static {
        String tablePrefix0 = ConfigOption.MYSQL_TABLE.get();
        if (!tablePrefix0.matches("^[_A-Za-z0-9]+$")) {
            SimplePets.getDebugLogger().debug(DebugLevel.WARNING, "Table prefix " + tablePrefix0 + " is not alphanumeric. Using simplepets...", true);
            TABLE_PREFIX = "simplepets";
        } else {
            TABLE_PREFIX = tablePrefix0;
        }

        boolean enabled = ConfigOption.MYSQL_ENABLED.get();
        DATABASE_NAME = ConfigOption.MYSQL_DATABASE.get();

        if (!enabled) {
            USE_SQLITE = true;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                USE_SQLITE = true;
            }
        }
    }
}
