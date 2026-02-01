package simplepets.brainsynder.api.pet.data;

import lib.brainsynder.item.ItemBuilder;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.pet.PetData;
import simplepets.brainsynder.api.pet.annotations.DisableDefault;

@DisableDefault
@Namespace(namespace = "half_scale")
public class HalfScaleData extends PetData<IEntityPet> {
    public HalfScaleData() {
        addDefaultItem("true", new ItemBuilder(Material.PLAYER_HEAD)
            .withName("&#c8c8c8{name}: &atrue").setTexture("http://textures.minecraft.net/texture/5aa7ebadfd28e58d8b8c1c595b09ff0101989f79ad6cdeb16aaed2a809874"));
        addDefaultItem("false", new ItemBuilder(Material.PLAYER_HEAD)
            .withName("&#c8c8c8{name}: &cfalse").setTexture("http://textures.minecraft.net/texture/76fdd4b13d54f6c91dd5fa765ec93dd9458b19f8aa34eeb5c80f455b119f278"));
    }

    @Override
    public Object getDefaultValue() {
        return false;
    }

    @Override
    public void onLeftClick(IEntityPet entity) {
        if (entity.isFullSize()) {
            entity.setPetScale(0.5);
        }else{
            entity.setPetScale(1.0);
        }
    }

    @Override
    public Object value(IEntityPet entity) {
        return !entity.isFullSize();
    }
}
