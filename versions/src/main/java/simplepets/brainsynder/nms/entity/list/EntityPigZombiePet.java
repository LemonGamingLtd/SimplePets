package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.hostile.IEntityPigZombiePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;

/**
 * NMS: {@link net.minecraft.world.entity.monster.ZombifiedPiglin}
 */
public class EntityPigZombiePet extends EntityZombiePet implements IEntityPigZombiePet {
    public EntityPigZombiePet(PetType type, PetUser user) {
        super(EntitySelector.ZOMBIFIED_PIGLIN, type, user);
    }
}
