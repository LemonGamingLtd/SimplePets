package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityHuskPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Husk}
 */
public class EntityHuskPet extends EntityZombiePet implements IEntityHuskPet {
    public EntityHuskPet(PetType type, PetUser user) {
        super(EntitySelector.HUSK, type, user);
    }
}
