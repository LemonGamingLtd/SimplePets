package simplepets.brainsynder.listeners.breaking;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.pet.CommandReason;
import simplepets.brainsynder.api.plugin.SimplePets;

public class DismountListener implements Listener {
    @EventHandler
    public void onDismount (EntityDismountEvent event) {
        if ((event.getEntity() instanceof Player) && SimplePets.isPetEntity(event.getDismounted())) {
            SimplePets.getUserManager().getPetUser((Player) event.getEntity()).ifPresent(user -> {
                SimplePets.getSpawnUtil().getHandle(event.getDismounted()).ifPresent(o -> {
                    IEntityPet pet = (IEntityPet) o;
                    SimplePets.getPetUtilities().runPetCommands(CommandReason.RIDE_DISMOUNT, user, pet.getPetType());
                });
            });
        }
    }
}
