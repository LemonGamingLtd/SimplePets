package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntitySalmonPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityFishPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.Salmon}
 */
public class EntitySalmonPet extends EntityFishPet implements IEntitySalmonPet {
    public EntitySalmonPet(PetType type, PetUser user) {
        super(EntityType.SALMON, type, user);
    }
}
