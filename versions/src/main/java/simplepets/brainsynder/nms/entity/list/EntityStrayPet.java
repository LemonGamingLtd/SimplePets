package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityStrayPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Stray}
 */
public class EntityStrayPet extends EntityPetOverride implements IEntityStrayPet {
    public EntityStrayPet(PetType type, PetUser user) {
        super(EntitySelector.STRAY, type, user);
    }
}
