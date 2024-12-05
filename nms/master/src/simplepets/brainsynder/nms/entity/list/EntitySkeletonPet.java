package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntitySkeletonPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Skeleton}
 */
public class EntitySkeletonPet extends EntityPetOverride implements IEntitySkeletonPet {
    public EntitySkeletonPet(PetType type, PetUser user) {
        super(EntityType.SKELETON, type, user);
    }
}
