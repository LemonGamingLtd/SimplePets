package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityEvokerPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.branch.EntityIllagerWizardPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Evoker}
 */
public class EntityEvokerPet extends EntityIllagerWizardPet implements IEntityEvokerPet {
    public EntityEvokerPet(PetType type, PetUser user) {
        super(EntitySelector.EVOKER, type, user);
    }
}
