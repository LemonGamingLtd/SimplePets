package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntityMulePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.branch.EntityDonkeyAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.horse.Mule}
 */
public class EntityMulePet extends EntityDonkeyAbstractPet implements IEntityMulePet {
    public EntityMulePet(PetType type, PetUser user) {
        super(EntityType.MULE, type, user);
    }
}
