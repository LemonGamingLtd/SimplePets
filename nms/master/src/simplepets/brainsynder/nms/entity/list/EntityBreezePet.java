package simplepets.brainsynder.nms.entity.list;

import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityBreezePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.breeze.Breeze}
 */
@SupportedVersion(version = ServerVersion.v1_21)
public class EntityBreezePet extends EntityPet implements IEntityBreezePet {
    public EntityBreezePet(PetType type, PetUser user) {
        super(EntityType.BREEZE, type, user);
    }
}
