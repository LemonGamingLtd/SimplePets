package simplepets.brainsynder.api.pet.data;

import lib.brainsynder.item.ItemBuilder;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.passive.IEntityAllayPet;
import simplepets.brainsynder.api.pet.PetData;

@Namespace(namespace = "dancing")
public class AllayDancingData extends PetData<IEntityAllayPet> {
    public AllayDancingData() {
        addDefaultItem("true", new ItemBuilder(Material.PLAYER_HEAD)
            .withName("&#c8c8c8{name}: &atrue")
            .setTexture("http://textures.minecraft.net/texture/98896605e41a1f4e2c3c92a964f391f4e61390cb10af2c0fab615a5d34e61074"));
        addDefaultItem("false", new ItemBuilder(Material.PLAYER_HEAD)
            .withName("&#c8c8c8{name}: &cfalse")
            .setTexture("http://textures.minecraft.net/texture/b3e7bba47b64f458579db865daeea4d6f8a4034153a543aedd8bf7ce0aeab7c8"));
    }

    @Override
    public Object getDefaultValue() {
        return false;
    }

    @Override
    public void onLeftClick(IEntityAllayPet entity) {
        entity.setDancing(!entity.isDancing());
    }

    @Override
    public Object value(IEntityAllayPet entity) {
        return entity.isDancing();
    }
}
