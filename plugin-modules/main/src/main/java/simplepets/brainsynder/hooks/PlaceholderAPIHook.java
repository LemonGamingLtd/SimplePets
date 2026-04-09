package simplepets.brainsynder.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.utils.Utilities;

import java.util.Optional;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "simplepets";
    }

    @Override
    public @NotNull String getAuthor() {
        return "brainsynder";
    }

    @Override
    public @NotNull String getVersion() {
        return SimplePets.getPlugin().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return null;
        Optional<PetUser> userOpt = SimplePets.getUserManager().getPetUser(player);
        if (userOpt.isEmpty()) return "false";
        PetUser user = userOpt.get();

        // %simplepets_has_pet% — true if the player has any pet currently spawned
        if (params.equals("has_pet")) return String.valueOf(user.hasPets());

        // %simplepets_has_access_<type>% — true if the player has access to the pet type
        if (params.startsWith("has_access_")) {
            PetType type = PetType.getPetType(params.substring("has_access_".length()).toUpperCase()).orElse(PetType.UNKNOWN);
            if (type == PetType.UNKNOWN) return "false";
            boolean purchased = user.getOwnedPets().contains(type) && ConfigOption.UTILIZE_PURCHASED_PETS.get();
            return String.valueOf(purchased || Utilities.hasPermission(player, type.getPermission()));
        }

        // %simplepets_has_spawned_<type>% — true if the specific pet type is currently spawned
        if (params.startsWith("has_spawned_")) {
            PetType type = PetType.getPetType(params.substring("has_spawned_".length()).toUpperCase()).orElse(PetType.UNKNOWN);
            if (type == PetType.UNKNOWN) return "false";
            return String.valueOf(user.hasPet(type));
        }
        return null;
    }
}
