package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityElderGuardianPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;

/**
 * NMS: {@link net.minecraft.world.entity.monster.ElderGuardian}
 */
public class EntityElderGuardianPet extends EntityGuardianPet implements IEntityElderGuardianPet {
    public EntityElderGuardianPet(PetType type, PetUser user) {
        super(EntitySelector.ELDER_GUARDIAN, type, user);
    }
}
