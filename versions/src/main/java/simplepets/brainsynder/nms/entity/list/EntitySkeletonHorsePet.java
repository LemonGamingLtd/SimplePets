package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.passive.IEntitySkeletonHorsePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.branch.EntityHorseAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.horse.SkeletonHorse}
 */
public class EntitySkeletonHorsePet extends EntityHorseAbstractPet implements IEntitySkeletonHorsePet {
    public EntitySkeletonHorsePet(PetType type, PetUser user) {
        super(EntitySelector.SKELETON_HORSE, type, user);
    }
}
