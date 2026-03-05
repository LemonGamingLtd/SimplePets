package simplepets.brainsynder.managers;

import lib.brainsynder.files.JsonFile;
import lib.brainsynder.json.JsonObject;
import lib.brainsynder.nbt.StorageTagTools;
import lib.brainsynder.particle.Particle;
import lib.brainsynder.particle.ParticleMaker;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.other.ParticleHandler;
import simplepets.brainsynder.api.plugin.config.ConfigOption;

import java.io.File;

public class ParticleManager implements ParticleHandler {
    private File folder;
    private ParticleMaker spawnParticle;
    private ParticleMaker renameParticle;
    private ParticleMaker failSpawnParticle;
    private ParticleMaker removeParticle;
    private ParticleMaker teleportParticle;
    private ParticleMaker taskFailParticle;

    public ParticleManager(PetCore plugin) {
        reload(plugin);
    }

    public void reload(PetCore plugin) {
        folder = new File(plugin.getDataFolder() + File.separator + "Particles");
        if (!folder.exists()) folder.mkdirs();

        spawnParticle = getCustomizedParticle(new ParticleMaker(Particle.INSTANT_EFFECT, 15, 1.3), "SpawnParticle");
        failSpawnParticle = getCustomizedParticle(new ParticleMaker(Particle.ASH, 10, 1.3), "FailedSpawnParticle");
        taskFailParticle = getCustomizedParticle(new ParticleMaker(Particle.SMOKE, 10, 1.3), "FailedTaskParticle");
        renameParticle = getCustomizedParticle(new ParticleMaker(Particle.HAPPY_VILLAGER, 10, 1.3), "RenameParticle");
        removeParticle = getCustomizedParticle(new ParticleMaker(Particle.LAVA, 20, 1), "RemoveParticle");
        teleportParticle = getCustomizedParticle(new ParticleMaker(Particle.PORTAL, 20, 1, 0.3, 1), "TeleportParticle");
    }

    private ParticleMaker getCustomizedParticle(ParticleMaker defaultParticle, String name) {
        try {
            JsonFile file = new JsonFile(new File(folder, name + ".json")) {
                @Override
                public void loadDefaults() {
                    setDefault("particle", StorageTagTools.toJsonObject(defaultParticle.toCompound()));
                }
            };
            if (!file.hasKey("particle")) return defaultParticle;

            return new ParticleMaker(StorageTagTools.fromJsonObject((JsonObject) file.getValue("particle")));
        } catch (Exception e) {
            return defaultParticle;
        }
    }

    @Override
    public void sendParticle(Reason reason, Player player, Location location) {
        if (player == null) return;
        switch (reason) {
            case SPAWN:
                if (ConfigOption.PARTICLES_SUMMON_TOGGLE.get())
                    spawnParticle.sendToPlayer(player, location);
                break;
            case FAILED:
                if (ConfigOption.PARTICLES_FAILED_TOGGLE.get())
                    failSpawnParticle.sendToPlayer(player, location);
                break;
            case RENAME:
                if (ConfigOption.PARTICLES_RENAME_TOGGLE.get())
                    renameParticle.sendToPlayer(player, location);
                break;
            case REMOVE:
                if (ConfigOption.PARTICLES_REMOVE_TOGGLE.get())
                    removeParticle.sendToPlayer(player, location);
                break;
            case TELEPORT:
                if (ConfigOption.PARTICLES_TELEPORT_TOGGLE.get())
                    teleportParticle.sendToPlayer(player, location);
                break;
            case TASK_FAILED:
                if (ConfigOption.PARTICLES_FAILED_TASK_TOGGLE.get())
                    taskFailParticle.sendToPlayer(player, location);
                break;
        }
    }

    public ParticleMaker getTaskFailParticle() {
        return taskFailParticle;
    }

    public ParticleMaker getTeleportParticle() {
        return teleportParticle;
    }

    public ParticleMaker getFailSpawnParticle() {
        return failSpawnParticle;
    }

    public ParticleMaker getRenameParticle() {
        return renameParticle;
    }

    public ParticleMaker getSpawnParticle() {
        return spawnParticle;
    }

    public ParticleMaker getRemoveParticle() {
        return removeParticle;
    }
}
