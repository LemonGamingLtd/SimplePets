package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.passive.IEntityCodPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityFishPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.Cod}
 */
public class EntityCodPet extends EntityFishPet implements IEntityCodPet {
    public EntityCodPet(PetType type, PetUser user) {
        super(EntitySelector.COD, type, user);
    }
}
