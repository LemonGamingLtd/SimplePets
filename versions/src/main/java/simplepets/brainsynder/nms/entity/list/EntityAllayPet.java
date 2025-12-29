package simplepets.brainsynder.nms.entity.list;

import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntityAllayPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityFlyablePet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.allay.Allay }
 */
@SupportedVersion(version = ServerVersion.v1_19)
public class EntityAllayPet extends EntityFlyablePet implements IEntityAllayPet {
    public EntityAllayPet(PetType type, PetUser user) {
        super(EntityType.ALLAY, type, user);
    }
}
