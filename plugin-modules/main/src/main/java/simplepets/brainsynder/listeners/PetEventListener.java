package simplepets.brainsynder.listeners;

import org.bsdevelopment.pluginutils.text.AdvString;
import org.bsdevelopment.pluginutils.text.Colorize;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplepets.brainsynder.api.event.user.PetRenameEvent;
import simplepets.brainsynder.api.plugin.config.ConfigOption;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetEventListener implements Listener {

    @EventHandler
    public void onRename(PetRenameEvent event) {
        String name = event.getName();
        // Name is null, no need to check any further...
        if (name == null) return;

        Player player = event.getUser().getPlayer();

        if (ConfigOption.RENAME_TRIM.get()) name = name.trim();
        if (player.hasPermission("pet.name.bypass")) return;
        String rawPattern = ConfigOption.RENAME_BLOCKED_PATTERN.get();
        if ((rawPattern != null) && (!rawPattern.isEmpty())) {
            if (event.getName().matches(rawPattern)) name = null;
        }

        // If the name is nullified by the above check just set the name and return...
        if (name == null) {
            event.setName(name);
            return;
        }

        List<String> blockedWords = ConfigOption.RENAME_BLOCKED_WORDS.get();
        if (!blockedWords.isEmpty()) {
            for (String word : blockedWords) {
                boolean ignoreCase = word.startsWith("^");
                if (ignoreCase) word = word.replaceFirst("\\^", "");

                if (word.startsWith("(") && word.endsWith(")")) {
                    if (ignoreCase && (name.toLowerCase().contains(AdvString.between("(", ")", word).toLowerCase()))) {
                        event.setCancelled(true);
                        return;
                    }

                    if (name.contains(AdvString.between("(", ")", word))) {
                        event.setCancelled(true);
                        return;
                    }
                }

                if (ignoreCase && (name.toLowerCase().contains(word.toLowerCase()))) {
                    event.setCancelled(true);
                    return;
                }
                if (name.contains(word)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }

        name = Colorize.translateBungeeHex(name);

        if (ConfigOption.RENAME_COLOR_ENABLED.get()) {
            if ((!player.hasPermission("pet.name.color.hex") || !ConfigOption.RENAME_COLOR_HEX.get())) {
                name = removeHexColor(name).replace('&', '§');
            }

            if (!player.hasPermission("pet.name.color")) {
                name = ChatColor.stripColor(name);
            }
        }

        if (ConfigOption.RENAME_LIMIT_CHARS_ENABLED.get()) {
            int limit = ConfigOption.RENAME_LIMIT_CHARS_NUMBER.get();
            if (name.length() > limit) {
                name = name.substring(0, limit);
            }
        }

        event.setName(name);
    }

    /**
     * A modified method from BSLib as a temp fix for it not removing post-translated colors
     */
    private String removeHexColor(String text) {
        // String is empty
        if ((text == null) || text.isEmpty()) return text;

        // The String does not contain any valid hex
        //if (!containsHexColors(text)) return text;

        // Replaces the COLOR_CHAR('§') to '&'
        text = text.replace(net.md_5.bungee.api.ChatColor.COLOR_CHAR, '&');
        Pattern word = Pattern.compile("&x");
        Matcher matcher = word.matcher(text);

        char[] chars = text.toCharArray();
        while (matcher.find()) {
            StringBuilder builder = new StringBuilder();
            int start = matcher.start();
            int end = (start + 14);

            // If the '&x' is at the end of the string ignore it
            if (end > text.length()) continue;
            for (int i = start; i < end; i++) builder.append(chars[i]);

            String hex = builder.toString();
            hex = hex.replace("&x", "").replace("&", "");
            text = text.replace(builder.toString(), "");
        }

        return text;
    }
}
