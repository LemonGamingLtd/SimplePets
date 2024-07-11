package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityStrayPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Stray}
 */
public class EntityStrayPet extends EntityPet implements IEntityStrayPet {
    public EntityStrayPet(PetType type, PetUser user) {
        super(EntityType.STRAY, type, user);
    }
}
