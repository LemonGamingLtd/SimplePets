package simplepets.brainsynder.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import simplepets.brainsynder.PetCore;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.files.Config;

public class DamageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerFall(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return; // Only listen if the cause is Fall Damage

        Entity vehicle = player.getVehicle();
        if (vehicle == null) return;
        if (!SimplePets.isPetEntity(vehicle)) return; // The vehicle is not a Pet

        SimplePets.getSpawnUtil().getHandle(vehicle).ifPresent(obj -> {
            IEntityPet pet = (IEntityPet) obj;
            SimplePets.getPetConfigManager().getPetConfig(pet.getPetType()).ifPresent(config -> { // Fetch the pets config
                Config configuration = PetCore.getInstance().getConfiguration();

                if (config.canFly(player)) { // Checks if a pet is a flyable pet
                    if (!ConfigOption.INSTANCE.PET_TOGGLES_FALL_DAMAGE_FLY.getValue()) return;
                    event.setCancelled(true);
                    return;
                }

                // Pet is not flyable...
                if (!ConfigOption.INSTANCE.PET_TOGGLES_FALL_DAMAGE_NON_FLY.getValue()) return;
                event.setCancelled(true);
            });
        });
    }
}
