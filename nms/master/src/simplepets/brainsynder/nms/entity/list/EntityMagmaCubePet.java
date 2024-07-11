package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityMagmaCubePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;

/**
 * NMS: {@link net.minecraft.world.entity.monster.MagmaCube}
 */
public class EntityMagmaCubePet extends EntitySlimePet implements IEntityMagmaCubePet {
    public EntityMagmaCubePet(PetType type, PetUser user) {
        super(EntityType.MAGMA_CUBE, type, user);
    }
}
