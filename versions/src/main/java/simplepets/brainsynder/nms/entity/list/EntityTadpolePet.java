package simplepets.brainsynder.nms.entity.list;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.passive.IEntityTadpolePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;
import simplepets.brainsynder.nms.entity.EntityFishPet;
import simplepets.brainsynder.nms.utils.PetDataAccess;

/**
 * NMS: {@link net.minecraft.world.entity.animal.frog.Tadpole}
 */
@VersionLimit(min = {1, 19, 0})
public class EntityTadpolePet extends EntityFishPet implements IEntityTadpolePet {
    private static final EntityDataAccessor<Boolean> AGE_LOCKED = SynchedEntityData.defineId(EntityTadpolePet.class, EntityDataSerializers.BOOLEAN);

    public EntityTadpolePet(PetType type, PetUser user) {
        super(EntitySelector.TADPOLE, type, user);
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(AGE_LOCKED, false);
    }
}
