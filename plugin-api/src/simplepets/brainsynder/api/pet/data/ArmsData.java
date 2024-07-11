package simplepets.brainsynder.api.pet.data;

import lib.brainsynder.item.ItemBuilder;
import org.bukkit.Material;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.hostile.IEntityZombiePet;
import simplepets.brainsynder.api.entity.misc.ISkeletonAbstract;
import simplepets.brainsynder.api.pet.PetData;

@Namespace(namespace = "raised_arms")
public class ArmsData extends PetData {
    public ArmsData() {
        addDefaultItem("true", new ItemBuilder(Material.STICK)
            .withName("&#c8c8c8{name}: &atrue"));
        addDefaultItem("false", new ItemBuilder(Material.STICK)
            .withName("&#c8c8c8{name}: &cfalse"));
    }

    @Override
    public Object getDefaultValue() {
        return false;
    }

    @Override
    public void onLeftClick(IEntityPet entity) {
        if (entity instanceof IEntityZombiePet zombie) {
            zombie.setArmsRaised(!zombie.isArmsRaised());
        } else if (entity instanceof ISkeletonAbstract skeleton) {
            skeleton.setArmsRaised(!skeleton.isArmsRaised());
        }
    }

    @Override
    public Object value(IEntityPet entity) {
        if (entity instanceof IEntityZombiePet zombie) {
            return zombie.isArmsRaised();
        } else if (entity instanceof ISkeletonAbstract skeleton) {
            return skeleton.isArmsRaised();
        }
        return false;
    }
}
