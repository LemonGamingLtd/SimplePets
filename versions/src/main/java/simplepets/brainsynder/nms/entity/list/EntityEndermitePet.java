package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityEndermitePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Endermite}
 */
public class EntityEndermitePet extends EntityPetOverride implements IEntityEndermitePet {
    public EntityEndermitePet(PetType type, PetUser user) {
        super(EntityType.ENDERMITE, type, user);
    }
}
