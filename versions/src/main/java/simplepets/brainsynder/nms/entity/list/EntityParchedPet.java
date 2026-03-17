package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.hostile.IEntityParchedPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.skeleton.Parched}
 */
@VersionLimit(min = {1, 21, 11})
public class EntityParchedPet extends EntityPetOverride implements IEntityParchedPet {
    public EntityParchedPet(PetType type, PetUser user) {
        super(EntityType.PARCHED, type, user);
    }
}
