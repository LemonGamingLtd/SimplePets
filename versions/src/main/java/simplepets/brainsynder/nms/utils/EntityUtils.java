package simplepets.brainsynder.nms.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.debug.DebugLevel;

import java.util.Random;

public class EntityUtils {
    private static final Random RANDOM;
    private static Scoreboard scoreboard;

    public static Team fetchTeam (Player player) {
        String key = "SimplePets-"+player.getName();
        Team team = getScoreboard().getTeam(key);
        if (team == null) {
            team = getScoreboard().registerNewTeam(key);
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            team.addEntry(player.getName());
            team.setCanSeeFriendlyInvisibles(true);
        }
        return team;
    }

    static {
        RANDOM = new Random();
        if (ConfigOption.PET_TOGGLES_GLOW_VANISH.get()) {
            SimplePets.getDebugLogger().debug(DebugLevel.WARNING, "GlowingEntities class is currently unavailable in this version...");
        }
    }

    public static Scoreboard getScoreboard () {
        if (scoreboard == null) scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        return scoreboard;
    }

    public static Random getRandom() {
        return RANDOM;
    }
}
