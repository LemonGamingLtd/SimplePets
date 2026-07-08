package simplepets.brainsynder.nms.entity.list;

import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.hostile.IEntityBreezePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.monster.breeze.Breeze}
 */
@VersionLimit(min = {1, 21, 0})
public class EntityBreezePet extends EntityPetOverride implements IEntityBreezePet {
    public EntityBreezePet(PetType type, PetUser user) {
        super(EntitySelector.BREEZE, type, user);
    }
}
