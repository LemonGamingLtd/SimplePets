package simplepets.brainsynder.api.plugin.config;

import com.google.common.collect.Lists;
import simplepets.brainsynder.api.plugin.config.internal.ConfigEntry;
import simplepets.brainsynder.api.plugin.config.internal.ConfigRegistry;

import java.util.List;

public interface MessageOption {
    ConfigRegistry REGISTRY = new ConfigRegistry();

    ConfigEntry<String> PREFIX = REGISTRY.register("prefix", "&eSimplePets &6>>",
            "Will replace the {prefix} placeholder");
    ConfigEntry<String> NO_PERMISSION = REGISTRY.register("no_permission", "{prefix} &cYou do not have permission.",
            "Message will be sent when the player does not have permission for when a permission is required");
    ConfigEntry<String> NO_PETS_UNLOCKED = REGISTRY.register("no_pets_unlocked", "{prefix} &cYou do not have any pets unlocked.",
            "This message will only be used if the player does not have any pets unlocked and 'Needs-Pet-Permission-for-GUI' is TRUE");

    ConfigEntry<String> SUMMONED_ALL_PETS = REGISTRY.register("summon.all_pets", "{prefix} &7Successfully summoned {count} pets!",
            "Message that will be sent when pets are spawned via '/pet summon all' (Mostly for OPs to show off)");
    ConfigEntry<String> SUMMONED_PET = REGISTRY.register("summon.pet", "{prefix} &7Successfully summoned the {type} pet!",
            "Message that will be sent when a pet is spawned via '/pet summon'");
    ConfigEntry<String> FAILED_SUMMON = REGISTRY.register("summon.failed", "{prefix} &cSorry, the {type} pet was unable to be spawned at the moment.",
            "Message that will be sent when a pet fails to spawn");
    ConfigEntry<String> CANT_SPAWN_MORE_PETS = REGISTRY.register("summon.cant_spawn_more_pets", "{prefix} &cYou can't spawn any more pets!",
            "Message that will be sent if a player attempts to spawn more pets than they are allowed.");

    ConfigEntry<String> PURCHASE_ADD = REGISTRY.register("purchased.added", "{prefix} &7{type} was added to the purchased pets of &c{player}",
            "Message that will be sent when a pet is added to the players purchased list (via '/pets purchased add')");
    ConfigEntry<String> PURCHASE_REMOVE = REGISTRY.register("purchased.removed", "{prefix} &7{type} was removed from the purchased pets of &c{player}",
            "Message that will be sent when a pet is removed from the players purchased list (via '/pets purchased remove')");
    ConfigEntry<String> PURCHASE_LIST_PREFIX = REGISTRY.register("purchased.list_prefix", "{prefix} &7Owned Pets: ",
            "Is what is sent before the pets are listed (via '/pets purchased list')");

    ConfigEntry<String> MISSING_PET_TYPE = REGISTRY.register("pet_type.missing", "{prefix} &cMissing pet type.",
            "The pet type is missing");
    ConfigEntry<String> INVALID_PET_TYPE = REGISTRY.register("pet_type.invalid", "{prefix} &cSorry, &7'{arg}' &cis not a valid pet type.",
            "The input is not a valid pet (spelling?)");
    ConfigEntry<String> PET_NOT_REGISTERED = REGISTRY.register("pet_type.not_registered", "{prefix} &cSorry, {type} is not registered.",
            "The selected pet is not supported for the servers version\n(Or is missing in the jar file [in case it is modified])");
    ConfigEntry<String> PET_NOT_REGISTERED_LORE = REGISTRY.register("pet_type.not_registered_lore", "&cNOT REGISTERED");
    ConfigEntry<String> PET_IN_DEVELOPMENT = REGISTRY.register("pet_type.in_development", "{prefix} &cSorry, {type} is currently in-development and not able to be used.",
            "The selected pet is in development for your version of SimplePets\nAKA we are still working on it expect issues");
    ConfigEntry<String> PET_NOT_SUPPORTED = REGISTRY.register("pet_type.not_supported", "{prefix} &cSorry, {type} is not supported for this version.");
    ConfigEntry<String> PET_NOT_SUPPORTED_LORE = REGISTRY.register("pet_type.not_supported_lore", "&cNOT SUPPORTED");

    ConfigEntry<String> INVALID_NBT = REGISTRY.register("nbt.invalid", "{prefix} &cInvalid nbt has been entered.",
            "Message that will show before the 'nbt error message'");
    ConfigEntry<String> INVALID_NBT_MESSAGE = REGISTRY.register("nbt.error", "{prefix} &c{message}",
            "The error message sent alongside the invalid nbt message");

    ConfigEntry<String> PLAYER_NOT_ONLINE = REGISTRY.register("player_not_online", "{prefix} &c{player} is not online (spelling?)",
            "Message that will be sent when the target player is not online");

    ConfigEntry<String> REMOVED_PET = REGISTRY.register("remove.removed_pet", "{prefix} &7Successfully removed the {type} pet!",
            "Message that will be sent when a pet is removed");
    ConfigEntry<String> REMOVED_ALL_PETS = REGISTRY.register("remove.all_pets", "{prefix} &7Successfully removed {count} pets!",
            "Message that will be sent when all pets are removed");
    ConfigEntry<String> REMOVED_NOT_SPAWNED = REGISTRY.register("remove.not_spawned", "{prefix} &cSorry, {type} is not spawned.",
            "Message that will be sent when attempting to remove a pet that is not spawned");

    ConfigEntry<String> MODIFY_COMPOUND = REGISTRY.register("modify.compound", "{prefix} &7NBT compound: &e{compound}",
            "Message contains what the player has set the pets data to\nSet this as an empty string \"\" to prevent it from being sent");
    ConfigEntry<String> MODIFY_APPLIED = REGISTRY.register("modify.applied", "{prefix} &7Data has been applied to the {type} pet!",
            "Message that will be sent when the compound is applied to the entity");

    ConfigEntry<String> RENAME_VIA_CHAT = REGISTRY.register("rename.via_chat", "{prefix} &7Type your pets new name in chat:",
            "Message that will be sent when the player is renaming the pet via chat");
    ConfigEntry<String> RENAME_VIA_CHAT_CANCEL = REGISTRY.register("rename.cancel", "{prefix} &cPet renaming has been canceled",
            "Message that will be sent when the player canceled renaming the pet");
    ConfigEntry<String> RENAME_ANVIL_TITLE = REGISTRY.register("rename.anvil.title", "&#de9790[] &#b35349Rename Pet",
            "The title for the pet rename Anvil GUI");
    ConfigEntry<String> RENAME_ANVIL_TAG = REGISTRY.register("rename.anvil.tag_name", "&#de9790NEW NAME",
            "The name for the NAME_TAG in the Anvil GUI");
    ConfigEntry<String> RENAME_DIALOG_TITLE = REGISTRY.register("rename.dialog.title", "Rename Pet",
            "The title shown in the rename Dialog GUI");
    ConfigEntry<String> RENAME_DIALOG_BODY = REGISTRY.register("rename.dialog.body", "Enter a new name for your {type} pet.\nType 'reset' to clear the name.",
            "The body text shown in the rename Dialog GUI\n  - {type} will be replaced with the pet type name");
    ConfigEntry<String> RENAME_DIALOG_INPUT_LABEL = REGISTRY.register("rename.dialog.input_label", "Pet Name",
            "The label shown above the text input in the rename Dialog GUI");
    ConfigEntry<String> RENAME_DIALOG_SUBMIT = REGISTRY.register("rename.dialog.submit_button", "Rename",
            "The label for the submit button in the rename Dialog GUI");
    ConfigEntry<List<String>> RENAME_SIGN_TEXT = REGISTRY.register("rename.sign.lines",
            Lists.newArrayList("{input}", "&l^^^^^^^^", "&9&lPlease Enter", "&9&lPet Name"),
            """
            The text that will be set for the sign
              - One line MUST have {input} to mark what line the player types the pets name
              - Hex colors can NOT be used for this
              - MUST have 4 lines""");

    ConfigEntry<String> PET_SAVES_LIMIT_REACHED = REGISTRY.register("pet-saves.limit-reached", "{prefix} &cYou have reached your limit for saving pet",
            "Message that will be sent when the player has reached their global limit for any pet type");
    ConfigEntry<String> PET_SAVES_LIMIT_REACHED_TYPE = REGISTRY.register("pet-saves.limit-reached-per-type", "{prefix} &cYou have reached your limit for saving {type} pets",
            "Message that will be sent when the player has reached their per-pet-type limit");

    ConfigEntry<String> PET_FILES_REGEN = REGISTRY.register("admin.regenerate.pets", "{prefix} &7The Pets folder has been regenerated to the default files.",
            "Message will be sent when the pets folder has been reset");
    ConfigEntry<String> INV_FILES_REGEN = REGISTRY.register("admin.regenerate.inventories", "{prefix} &7The Inventories folder has been regenerated to the default files.",
            "Message will be sent when the inventories folder has been reset");
    ConfigEntry<String> ITEM_FILES_REGEN = REGISTRY.register("admin.regenerate.items", "{prefix} &7The Items folder has been regenerated to the default files.",
            "Message will be sent when the items folder has been reset");
    ConfigEntry<String> PARTICLE_FILES_REGEN = REGISTRY.register("admin.regenerate.particles", "{prefix} &7The Particles folder has been regenerated to the default files.",
            "Message will be sent when the particles folder has been reset");
    ConfigEntry<String> PET_TYPE_FILE_REGEN = REGISTRY.register("admin.regenerate.pet_type", "{prefix} &7The file for the {type} pet has been reset to the default file.",
            "Message will be sent when the selected pet file has been reset");

    ConfigEntry<String> CONFIG_RELOADED = REGISTRY.register("admin.reload.config", "{prefix} &7The plugin configuration has been reloaded!",
            "Message that will be sent when the main config has been reloaded.");
    ConfigEntry<String> MESSAGES_RELOADED = REGISTRY.register("admin.reload.messages", "{prefix} &7The plugin's message configuration has been reloaded!",
            "Message that will be sent when the messages config has been reloaded.");
    ConfigEntry<String> INVENTORIES_RELOADED = REGISTRY.register("admin.reload.inventories", "{prefix} &7Items and inventories have been reloaded!",
            "Message that will be sent when the inventories manager has been reloaded.");
    ConfigEntry<String> PARTICLES_RELOADED = REGISTRY.register("admin.reload.particles", "{prefix} &7Particles have been reloaded!",
            "Message that will be sent when the particles manager has been reloaded.");
    ConfigEntry<String> PETS_RELOADED = REGISTRY.register("admin.reload.pets", "{prefix} &7Pets have been reloaded!",
            "Message that will be sent when the pets manager has been reloaded.");
    ConfigEntry<String> ALL_RELOADED = REGISTRY.register("admin.reload.all", "{prefix} &7All plugin elements have been reloaded!",
            "Message that will be sent when all plugin elements have been reloaded.");

    ConfigEntry<String> CONFIG_UNKNOWN_KEY = REGISTRY.register("admin.pet-config.unknown-key", "{prefix} &7{key} &cis not a key for the pets json file.",
            "The key entered is not in the selected pets json file");
    ConfigEntry<String> CONFIG_INVALID_BOOLEAN = REGISTRY.register("admin.pet-config.invalid-boolean", "{prefix} &7{value} &cis not a valid boolean, please use true/false",
            "The value entered is not a boolean (aka it is not true or false)");
    ConfigEntry<String> CONFIG_INVALID_INT = REGISTRY.register("admin.pet-config.invalid-integer", "{prefix} &7{value} &cis not a valid integer.",
            "The value entered is not a valid integer (1,2,3)");
    ConfigEntry<String> CONFIG_INVALID_DOUBLE = REGISTRY.register("admin.pet-config.invalid-double", "{prefix} &7{value} &cis not a valid double.",
            "The value entered is not a valid double (0.1, 0.02, 0.003)");
    ConfigEntry<String> CONFIG_UNABLE_TO_UPDATE = REGISTRY.register("admin.pet-config.unable-to-update", "{prefix} &cUnable to update this key.",
            "The key entered is not able to be updated via the command (probably is an array or an object)");
    ConfigEntry<String> CONFIG_VALUE_UPDATED = REGISTRY.register("admin.pet-config.value-set", "{prefix} &a{key} &7has been set to &e{value}",
            """
            The key has been updated with the new value

            Placeholders:
            {key} - The target key
            {value} - The new value
            {type} - The type of pet selected
            """);
    ConfigEntry<String> CONFIG_VALUE_RESET = REGISTRY.register("admin.pet-config.value-reset", "{prefix} &a{key} &7has been reset to the default value",
            """
            The key has been reset with the default value

            Placeholders:
            {key} - The target key
            {value} - The new value
            {type} - The type of pet selected
            """);

    ConfigEntry<String> PET_ON_COOLDOWN = REGISTRY.register("cooldown.on_cooldown",
            "{prefix} &cYou must wait &e{seconds} &csecond(s) before changing your pet.",
            "Sent when a player tries to spawn a pet while on cooldown. {seconds} = remaining seconds.");
}
