package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntityDrownedPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Drowned}
 */
public class EntityDrownedPet extends EntityZombiePet implements IEntityDrownedPet {
    public EntityDrownedPet(PetType type, PetUser user) {
        super(EntityType.DROWNED, type, user);
    }
}
