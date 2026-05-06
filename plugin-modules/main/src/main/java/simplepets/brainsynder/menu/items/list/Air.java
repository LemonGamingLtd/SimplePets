package simplepets.brainsynder.menu.items.list;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.inventory.CustomInventory;
import simplepets.brainsynder.api.inventory.Item;
import simplepets.brainsynder.api.user.PetUser;

import java.io.File;

@Namespace(namespace = "air")
public class Air extends Item {
    public Air(File file) {
        super(file);
    }

    @Override
    public void onClick(PetUser user, CustomInventory inventory, IEntityPet pet) {

    }

    @Override
    public void loadDefaults() {
        defaults.add("_COMMENT_", "This not editable simply because you can not modify air :P");
    }

    @Override
    public ItemBuilder getItemBuilder() {
        return ItemBuilder.of(Material.AIR);
    }

    @Override
    public ItemBuilder getDefaultItem() {
        return ItemBuilder.of(Material.AIR);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
