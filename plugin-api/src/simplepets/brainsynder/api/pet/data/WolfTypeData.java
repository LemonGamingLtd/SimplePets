package simplepets.brainsynder.api.pet.data;

import lib.brainsynder.item.ItemBuilder;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.passive.IEntityWolfPet;
import simplepets.brainsynder.api.pet.PetData;
import simplepets.brainsynder.api.wrappers.ArmadilloPhase;
import simplepets.brainsynder.api.wrappers.WolfType;

@Namespace(namespace = "type")
public class WolfTypeData extends PetData<IEntityWolfPet> {
    public WolfTypeData() {
        for (WolfType type : WolfType.values()) {
            addDefaultItem(type.name(), new ItemBuilder(Material.PLAYER_HEAD)
                .withName("&#c8c8c8{name}: &a" + type.name())
                .setTexture(type.getTexture()));
        }
    }

    @Override
    public Object getDefaultValue() {
        return ArmadilloPhase.STANDING;
    }

    @Override
    public void onRightClick(IEntityWolfPet entity) {
        entity.setWolfType(WolfType.getPrevious(entity.getWolfType()));
    }

    @Override
    public void onLeftClick(IEntityWolfPet entity) {
        entity.setWolfType(WolfType.getNext(entity.getWolfType()));
    }

    @Override
    public Object value(IEntityWolfPet entity) {
        return entity.getWolfType();
    }
}
