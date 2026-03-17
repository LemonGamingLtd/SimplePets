package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.passive.IEntityNautilusPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.branch.EntityNautilusAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.animal.nautilus.Nautilus }
 */
@VersionLimit(min = {1, 21, 11})
public class EntityNautilusPet extends EntityNautilusAbstractPet implements IEntityNautilusPet {
    public EntityNautilusPet(PetType type, PetUser user) {
        super(EntityType.NAUTILUS, type, user);
    }
}
