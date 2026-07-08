package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.passive.IEntityOcelotPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityAgeablePet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.Ocelot}
 */
public class EntityOcelotPet extends EntityAgeablePet implements IEntityOcelotPet {
    public EntityOcelotPet(PetType type, PetUser user) {
        super(EntitySelector.OCELOT, type, user);
    }
}
