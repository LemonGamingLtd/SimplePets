package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.passive.IEntityTadpolePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityFishPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.frog.Tadpole}
 */
@VersionLimit(min = {1, 19, 0})
public class EntityTadpolePet extends EntityFishPet implements IEntityTadpolePet {
    public EntityTadpolePet(PetType type, PetUser user) {
        super(EntityType.TADPOLE, type, user);
    }
}
