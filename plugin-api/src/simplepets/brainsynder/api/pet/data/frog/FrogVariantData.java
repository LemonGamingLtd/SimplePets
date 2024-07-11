package simplepets.brainsynder.api.pet.data.frog;

import lib.brainsynder.item.ItemBuilder;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.passive.IEntityFrogPet;
import simplepets.brainsynder.api.pet.PetData;
import simplepets.brainsynder.api.wrappers.FrogType;

@Namespace(namespace = "variant")
public class FrogVariantData extends PetData<IEntityFrogPet> {
    public FrogVariantData() {
        for (FrogType type : FrogType.values()) {
            addDefaultItem(type.name(), new ItemBuilder(Material.PLAYER_HEAD)
                .withName("&#c8c8c8{name}: &a" + type.name())
                .setTexture(type.getTexture()));
        }
    }

    @Override
    public Object getDefaultValue() {
        return FrogType.TEMPERATE;
    }

    @Override
    public void onLeftClick(IEntityFrogPet entity) {
        entity.setVariant(FrogType.getNext(entity.getVariant()));
    }

    @Override
    public Object value(IEntityFrogPet entity) {
        return entity.getVariant();
    }
}
