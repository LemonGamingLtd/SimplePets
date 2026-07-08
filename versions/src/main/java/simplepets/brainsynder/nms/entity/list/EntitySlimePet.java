package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntitySlimePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.branch.EntityCubeAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.cubemob.Slime}
 */
public class EntitySlimePet extends EntityCubeAbstractPet implements IEntitySlimePet {
    public EntitySlimePet(PetType type, PetUser user) {
        super(EntitySelector.SLIME, type, user);
    }
}
