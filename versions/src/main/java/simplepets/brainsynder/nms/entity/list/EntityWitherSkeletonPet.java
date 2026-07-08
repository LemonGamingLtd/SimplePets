package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityWitherSkeletonPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.WitherSkeleton}
 */
public class EntityWitherSkeletonPet extends EntityPetOverride implements IEntityWitherSkeletonPet {
    public EntityWitherSkeletonPet(PetType type, PetUser user) {
        super(EntitySelector.WITHER_SKELETON, type, user);
    }
}
