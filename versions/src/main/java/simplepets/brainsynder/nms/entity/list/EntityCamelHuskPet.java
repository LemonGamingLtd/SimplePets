package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.passive.IEntityCamelHuskPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;

/**
 * NMS: {@link net.minecraft.world.entity.animal.camel.CamelHusk}
 */
@VersionLimit(min = {1, 21, 11})
public class EntityCamelHuskPet extends EntityCamelPet implements IEntityCamelHuskPet {
    public EntityCamelHuskPet(PetType type, PetUser user) {
        this(EntitySelector.CAMEL_HUSK, type, user);
    }

    public EntityCamelHuskPet(EntityType<? extends Mob> entityType, PetType type, PetUser user) {
        super(entityType, type, user);
        doIndirectAttach = false;
    }
}
