package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.passive.IEntityIronGolemPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.animal.golem.IronGolem}
 */
// TODO: With the new HalfScale DataItem, the golem seems to be getting cracked when its not supposed to be...
public class EntityIronGolemPet extends EntityPetOverride implements IEntityIronGolemPet {
    public EntityIronGolemPet(PetType type, PetUser user) {
        super(EntitySelector.IRON_GOLEM, type, user);
    }
}
