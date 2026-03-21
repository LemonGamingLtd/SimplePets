package simplepets.brainsynder.menu.items.list;

import lib.brainsynder.item.ItemBuilder;
import org.bsdevelopment.pluginutils.PluginUtilities;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.inventory.CustomInventory;
import simplepets.brainsynder.api.inventory.Item;
import simplepets.brainsynder.api.pet.IPetConfig;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.plugin.config.ConfigOption;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.managers.InventoryManager;
import simplepets.brainsynder.menu.inventory.PetSelectorMenu;

import java.io.File;

@Namespace(namespace = "ride")
public class Ride extends Item {
    public Ride(File file) {
        super(file);
    }

    @Override
    public ItemBuilder getDefaultItem() {
        return new ItemBuilder(Material.DIAMOND_HORSE_ARMOR).withName("&#e3c79a&lRide Your Pet");
    }

    @Override
    public boolean addItemToInv(PetUser user, CustomInventory inventory) {
        for (IEntityPet entity : user.getPetEntities()) {
            IPetConfig config = SimplePets.getPetConfigManager().getPetConfig(entity.getPetType()).orElse(null);
            if (config == null) continue;
            if (config.canMount(user.getPlayer())) return true;
        }
        return ConfigOption.PET_TOGGLES_MOUNTABLE.get();
    }

    @Override
    public void onClick(PetUser masterUser, CustomInventory inventory, IEntityPet pet) {
        if (!masterUser.hasPets()) return;

        if (pet != null) {
            if (ConfigOption.MISC_TOGGLES_AUTO_CLOSE_RIDE.get())
                masterUser.getPlayer().closeInventory();
            PluginUtilities.getScheduler().runTaskLater(() ->
                masterUser.setPetVehicle(pet.getPetType(), !masterUser.isPetVehicle(pet.getPetType())), 2);
            return;
        }

        if (masterUser.getPetEntities().size() == 1) {
            if (ConfigOption.MISC_TOGGLES_AUTO_CLOSE_RIDE.get())
                masterUser.getPlayer().closeInventory();
            PluginUtilities.getScheduler().runTaskLater(() ->
                masterUser.getPetEntities().stream().findFirst().ifPresent(iEntityPet ->
                    masterUser.setPetVehicle(iEntityPet.getPetType(), !masterUser.isPetVehicle(iEntityPet.getPetType()))), 2);
            return;
        }
        PetSelectorMenu menu = InventoryManager.SELECTOR;
        menu.setTask(masterUser.getPlayer().getName(), (user, type) -> {
            if (ConfigOption.MISC_TOGGLES_AUTO_CLOSE_RIDE.get())
                user.getPlayer().closeInventory();
            PluginUtilities.getScheduler().runTaskLater(() ->
                user.setPetVehicle(type, !user.isPetVehicle(type)), 2);
        });
        menu.open(masterUser, 1, inventory.getTitle());
    }
}
