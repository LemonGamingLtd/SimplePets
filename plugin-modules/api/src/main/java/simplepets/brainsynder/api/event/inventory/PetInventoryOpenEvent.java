package simplepets.brainsynder.api.event.inventory;

import org.bukkit.inventory.ItemStack;
import simplepets.brainsynder.api.event.CancellablePetEvent;
import simplepets.brainsynder.api.user.PetUser;

import java.util.ArrayList;
import java.util.List;

/**
 * This event is called when the player opens the pet selector GUI
 */
public class PetInventoryOpenEvent extends CancellablePetEvent {
    private List<PetTypeStorage> shownPetTypes = new ArrayList<>();
    private final PetUser user;
    private List<ItemStack> items = new ArrayList<>();

    public PetInventoryOpenEvent(List<PetTypeStorage> petTypes, PetUser user) {
        this.shownPetTypes = new ArrayList<>(petTypes);
        this.user = user;
        for (PetTypeStorage type : shownPetTypes) {
            items.add(type.getItem());
        }
    }

    public List<PetTypeStorage> getShownPetTypes() {return this.shownPetTypes;}

    public PetUser getUser() {return this.user;}

    public List<ItemStack> getItems() {return this.items;}

    public void setItems(List<ItemStack> items) {this.items = items;}
}
