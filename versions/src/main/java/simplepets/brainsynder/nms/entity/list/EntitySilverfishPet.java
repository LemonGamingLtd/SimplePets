package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntitySilverfishPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Silverfish}
 */
public class EntitySilverfishPet extends EntityPetOverride implements IEntitySilverfishPet {
    public EntitySilverfishPet(PetType type, PetUser user) {
        super(EntityType.SILVERFISH, type, user);
    }
}
