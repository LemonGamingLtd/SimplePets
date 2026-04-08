package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityIllusionerPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.branch.EntityIllagerWizardPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Illusioner}
 */
public class EntityIllusionerPet extends EntityIllagerWizardPet implements IEntityIllusionerPet {
    public EntityIllusionerPet(PetType type, PetUser user) {
        super(EntityType.ILLUSIONER, type, user);
    }
}
