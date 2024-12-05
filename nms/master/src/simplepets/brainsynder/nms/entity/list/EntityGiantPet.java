package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityGiantPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Giant}
 */
public class EntityGiantPet extends EntityPetOverride implements IEntityGiantPet {
    public EntityGiantPet(PetType type, PetUser user) {
        super(EntityType.GIANT, type, user);
    }
}
