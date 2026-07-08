package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityDrownedPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Drowned}
 */
public class EntityDrownedPet extends EntityZombiePet implements IEntityDrownedPet {
    public EntityDrownedPet(PetType type, PetUser user) {
        super(EntitySelector.DROWNED, type, user);
    }
}
