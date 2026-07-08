package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityPillagerPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.branch.EntityIllagerAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Pillager}
 */
public class EntityPillagerPet extends EntityIllagerAbstractPet implements IEntityPillagerPet {
    public EntityPillagerPet(PetType type, PetUser user) {
        super(EntitySelector.PILLAGER, type, user);
    }
}
