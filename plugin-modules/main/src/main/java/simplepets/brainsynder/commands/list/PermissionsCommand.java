package simplepets.brainsynder.commands.list;

import org.bsdevelopment.pluginutils.command.CommandBuilder;
import org.bukkit.ChatColor;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.addon.AddonPermissions;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.plugin.config.MessageOption;
import simplepets.brainsynder.commands.PetCommandClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PermissionsCommand implements PetCommandClass {

    /**
     * Represents a single command's permission metadata.
     * {commandName, permissionSuffix, adminCommand, defaultAllow, additionalPermissions}
     */
    private static final Object[][] COMMAND_DATA = {
        {"help",        "help",        false, true,  new String[]{}},
        {"summon",      "summon",      false, true,  new String[]{"all", "other", "nbt"}},
        {"remove",      "remove",      false, true,  new String[]{"other"}},
        {"list",        "list",        false, true,  new String[]{}},
        {"gui",         "gui",         false, true,  new String[]{"other"}},
        {"data",        "data",        false, false, new String[]{"target"}},
        {"rename",      "rename",      false, true,  new String[]{"other"}},
        {"modify",      "modify",      false, false, new String[]{"target"}},
        {"purchased",   "purchased",   true,  false, new String[]{"add", "remove", "list", "list.other"}},
        {"permissions", "permissions", true,  false, new String[]{}},
        {"regenerate",  "regenerate",  true,  false, new String[]{}},
        {"debug",       "debug",       true,  false, new String[]{}},
        {"addon",       "addon",       false, false, new String[]{"install", "update", "reload"}},
        {"database",    "database",    false, false, new String[]{"removenpcs", "removeduplicates", "findduplicates"}},
        {"reload",      "reload",      true,  false, new String[]{}},
        {"petconfig",   "petconfig",   true,  false, new String[]{}},
        {"premium",     "premium",     true,  false, new String[]{}},
    };

    @Override
    public CommandBuilder build() {
        return CommandBuilder.create("permissions")
                .withPermission("pet.commands.permissions")
                .withDescription("Generates a file that contains all the permissions in the plugin")
                .executes((sender, args) -> {
                    generatePluginPermissions(true);
                    sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX)
                            + ChatColor.GRAY + "Generated the permissions.yml file");
                })
                .withSubcommand(CommandBuilder.create("dev")
                        .withPermission("pet.commands.permissions")
                        .withDescription("Generates permissions without wildcard placeholders")
                        .executes((sender, args) -> {
                            generatePluginPermissions(false);
                            sender.sendMessage(PetCore.getInstance().getMessageFile().getTranslation(MessageOption.PREFIX)
                                    + ChatColor.GRAY + "Generated the permissions.yml file (dev mode)");
                        }));
    }

    private void addPermission(StringBuilder builder, String permission, String comment, String defaultType) {
        builder.append("    ").append(permission).append(": ");
        if ((comment != null) && (!comment.isEmpty())) builder.append(" # ").append(comment);
        builder.append("\n        default: ").append(defaultType).append("\n");
    }

    private void generatePluginPermissions(boolean notDeveloper) {
        StringBuilder def = new StringBuilder();
        def.append("        default: false").append("\n").append("        children:").append("\n");

        StringBuilder master = new StringBuilder();
        addPermission(master, "pet.amount.bypass", "This permission bypasses the limit of how many pets can be spawned", "op");
        if (notDeveloper)
            addPermission(master, "pet.amount.<number>", "This permission sets how many pets the player can have spawned", "false");
        addPermission(master, "pet.name.bypass", "This permission bypasses any of the pet renaming checks", "op");
        addPermission(master, "pet.name.color", "This permission is to allow players to add color codes when renaming their pet", "true");
        addPermission(master, "pet.name.color.hex", "This permission is to allow players to add HEX color codes when renaming their pet (Eg: &#FFFFFF)", "op");
        master.append("\n\n");

        StringBuilder hostile = new StringBuilder();
        StringBuilder passive = new StringBuilder();
        StringBuilder allAllowData = new StringBuilder();
        StringBuilder commandBuilder = new StringBuilder();
        List<StringBuilder> pets = new ArrayList<>();
        List<StringBuilder> other = new ArrayList<>();

        for (Object[] data : COMMAND_DATA) {
            String commandName = (String) data[0];
            String permSuffix = (String) data[1];
            boolean adminCommand = (boolean) data[2];
            boolean defaultAllow = (boolean) data[3];
            String[] additional = (String[]) data[4];

            String permission = "pet.commands." + permSuffix;
            commandBuilder.append("            ").append(permission).append(": true\n");

            for (String addition : additional) {
                if (addition.isEmpty()) continue;
                String comment = "";
                if (addition.equals("all_other")) comment = "  # Will allow the player spawn/change for all the selected player pets";
                if (addition.equals("all")) comment = "  # Will allow the player to run the command for all the pets";
                if (addition.equals("nbt")) comment = "  # Will allow the player to input NBT data to modify the pet (Will bypass the data permissions for the pet)";
                if (addition.equals("other")) comment = "  # Will allow the player to spawn/change other players data";
                commandBuilder.append("            ").append(permission).append(".").append(addition).append(": true").append(comment).append("\n");
            }

            String allowDefault = "false # Not given to players by default";
            if (adminCommand) {
                allowDefault = "op # Only Operators should be given this permission";
            } else if (defaultAllow) {
                allowDefault = "true # Allows players to use them by default";
            }

            master.append("    ").append(permission).append(": # Grants access to the '/pet ").append(commandName).append("' command\n")
                    .append("        default: ").append(allowDefault).append("\n\n");
        }

        master.append("\n")
                .append("    pet.commands.*:  # Grants the player to use all commands (NOT recommended)").append("\n")
                .append(def).append(commandBuilder).append("\n\n");

        if (notDeveloper) {
            if ((!AddonPermissions.getPermissions().isEmpty()) || (!AddonPermissions.getParentPermissions().isEmpty()))
                master.append("    # Here is all the Addon permissions (if there are any)\n\n");
            AddonPermissions.getPermissions().forEach((addonName, list) -> {
                master.append("    # Permissions for the ").append(addonName).append(" addon\n");
                list.forEach(permData -> {
                    String description = " # " + permData.getDescription();
                    if (description.equals(" # ")) description = "";
                    master.append("    ").append(permData.getPermission()).append(":").append(description).append("\n")
                            .append("        default: ").append(permData.getType().toString()).append("\n");
                });
            });

            AddonPermissions.getParentPermissions().forEach((addonName, permissionMap) -> {
                if (!master.toString().contains("    # Permissions for the " + addonName + " addon"))
                    master.append("    # Permissions for the ").append(addonName).append(" addon\n");
                permissionMap.forEach((parent, children) -> {
                    String parentDescription = " # " + parent.getDescription();
                    if (parentDescription.equals(" # ")) parentDescription = "";
                    master.append("    ").append(parent.getPermission()).append(":  ").append(parentDescription).append("\n");
                    children.forEach(permData -> {
                        String description = " # " + permData.getDescription();
                        if (description.equals(" # ")) description = "";
                        master.append("        ").append(permData.getPermission()).append(": ").append(description).append("\n");
                    });
                });
            });
            master.append("\n");
        }

        for (PetType type : PetType.values()) {
            if (type == PetType.UNKNOWN) continue;
            String path = type.getEntityClass().getName();
            String permission = type.getPermission();
            if (path.contains("hostile")) {
                hostile.append("            ").append(permission).append(".*: true\n");
            } else if (path.contains("passive") || path.contains("ambient")) {
                passive.append("            ").append(permission).append(".*: true\n");
            }
            allAllowData.append("            ").append(permission).append(".data.*: true\n");

            StringBuilder builder = new StringBuilder();
            StringBuilder allData = new StringBuilder();

            allData.append("    ").append(permission).append(".data.*:  # Grants access to all the data permissions for this pet").append("\n");
            allData.append(def);

            builder.append("    ").append(permission).append(".*:  # Will grant access to spawn the pet as well as all the other permissions for this pet").append("\n");
            builder.append(def);
            builder.append("            ").append(permission).append(": true  # Will allow ").append(type.getName()).append(" to be spawned (if enabled)\n");
            builder.append("            ").append(permission).append(".fly: true  # Will allow ").append(type.getName()).append(" to fly (if enabled)\n");
            builder.append("            ").append(permission).append(".hat: true  # Will allow ").append(type.getName()).append(" to be a hat (if enabled)\n");
            builder.append("            ").append(permission).append(".mount: true  # Will allow ").append(type.getName()).append(" to be mounted (if enabled)\n");
            builder.append("            ").append(permission).append(".data.*").append(": true\n");
            type.getPetData().forEach(petData -> {
                String name = petData.namespace();
                allData.append("            ").append(permission).append(".data.").append(name).append(": true\n");
            });
            other.add(allData);
            pets.add(builder);
        }

        List<StringBuilder> fly = new ArrayList<>();
        fly.add(new StringBuilder()
                .append("    pet.type.*.fly:  # Will allow all pets to fly (if enabled)").append("\n")
                .append("        default: false").append("\n")
                .append("        children:").append("\n"));
        for (PetType type : PetType.values()) {
            if (type == PetType.UNKNOWN) continue;
            fly.add(new StringBuilder().append("            ").append(type.getPermission()).append(".fly: true").append("\n"));
        }

        List<StringBuilder> hat = new ArrayList<>();
        hat.add(new StringBuilder()
                .append("    pet.type.*.hat:  # Will allow all pets to be a hat (if enabled)").append("\n")
                .append("        default: false").append("\n")
                .append("        children:").append("\n"));
        for (PetType type : PetType.values()) {
            if (type == PetType.UNKNOWN) continue;
            hat.add(new StringBuilder().append("            ").append(type.getPermission()).append(".hat: true").append("\n"));
        }

        List<StringBuilder> savesBypass = new ArrayList<>();
        savesBypass.add(new StringBuilder()
                .append("    pet.saves.bypass:  # Will allow all player to bypass the saves limit (if enabled)").append("\n")
                .append("        default: false").append("\n")
                .append("        children:").append("\n"));
        for (PetType type : PetType.values()) {
            if (type == PetType.UNKNOWN) continue;
            savesBypass.add(new StringBuilder()
                    .append("            pet.saves.").append(type.name().toLowerCase().replace("_", "")).append(".bypass: true").append("\n"));
        }

        List<StringBuilder> mount = new ArrayList<>();
        mount.add(new StringBuilder()
                .append("    pet.type.*.mount:  # Will allow all pets to be mounted (if enabled)").append("\n")
                .append("        default: false").append("\n")
                .append("        children:").append("\n"));
        for (PetType type : PetType.values()) {
            if (type == PetType.UNKNOWN) continue;
            mount.add(new StringBuilder().append("            ").append(type.getPermission()).append(".mount: true").append("\n"));
        }

        savesBypass.forEach(master::append);

        master.append("\n")
                .append("    pet.type.*:  # Grants access to all pets").append("\n").append(def)
                .append("            pet.type.passive: true").append("\n")
                .append("            pet.type.hostile: true").append("\n")
                .append("    pet.type.passive:  # Will grant access to all pets that would be passive in vanilla").append("\n")
                .append(def).append(passive).append("\n\n")
                .append("    pet.type.hostile:  # Will grant access to all the pets that would be hostile in vanilla").append("\n")
                .append(def).append(hostile).append("\n\n")
                .append("    pet.type.*.data.*:  # Will grant all data permissions for all pets").append("\n")
                .append(def).append(allAllowData).append("\n\n");

        fly.forEach(master::append);
        master.append("\n\n");
        hat.forEach(master::append);
        master.append("\n\n");
        mount.forEach(master::append);
        master.append("\n\n");
        pets.forEach(sb -> master.append(sb).append("\n\n"));
        other.forEach(sb -> master.append(sb).append("\n\n"));

        log(new File(PetCore.getInstance().getDataFolder() + File.separator + "Generated Files" + File.separator), "permissions.yml", master.toString());
    }

    private void log(File folder, String fileName, String message) {
        try {
            if (!folder.exists()) folder.mkdirs();
            File saveTo = new File(folder, fileName);
            if (saveTo.exists()) saveTo.delete();
            saveTo.createNewFile();
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
