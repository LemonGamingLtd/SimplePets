package simplepets.brainsynder.api.pet.data.villager;

import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.entity.misc.IProfession;
import simplepets.brainsynder.api.pet.PetData;
import simplepets.brainsynder.api.wrappers.villager.VillagerLevel;

@Namespace(namespace = "level")
public class VillagerLevelData extends PetData<IProfession> {
    public VillagerLevelData() {
        for (VillagerLevel type : VillagerLevel.values()) {
            addDefaultItem(type.name(), type.getIcon()
                .withName("&#c8c8c8{name}: &a" + type.name()));
        }
    }

    @Override
    public Object getDefaultValue() {
        return VillagerLevel.NOVICE;
    }

    @Override
    public void onLeftClick(IProfession entity) {
        entity.setMasteryLevel(VillagerLevel.getNext(entity.getMasteryLevel()));
    }

    @Override
    public void onRightClick(IProfession entity) {
        entity.setMasteryLevel(VillagerLevel.getPrevious(entity.getMasteryLevel()));
    }

    @Override
    public Object value(IProfession entity) {
        return entity.getMasteryLevel();
    }
}
