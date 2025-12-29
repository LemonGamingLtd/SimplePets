package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntityDonkeyPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.branch.EntityDonkeyAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.horse.Donkey}
 */
public class EntityDonkeyPet extends EntityDonkeyAbstractPet implements IEntityDonkeyPet {
    public EntityDonkeyPet(PetType type, PetUser user) {
        super(EntityType.DONKEY, type, user);
    }
}
