package simplepets.brainsynder.nms.entity.list;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.hostile.IEntityCreakingPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;
import simplepets.brainsynder.nms.utils.PetDataAccess;

/**
 * NMS: {@link net.minecraft.world.entity.monster.creaking.Creaking}
 */
@VersionLimit(min = {1, 21, 4})
public class EntityCreakingPet extends EntityPetOverride implements IEntityCreakingPet {
    private static final EntityDataAccessor<Boolean> CAN_MOVE = SynchedEntityData.defineId(EntityCreakingPet.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ACTIVE = SynchedEntityData.defineId(EntityCreakingPet.class, EntityDataSerializers.BOOLEAN);

    public EntityCreakingPet(PetType type, PetUser user) {
        super(EntityType.CREAKING, type, user);
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(CAN_MOVE, true);
        dataAccess.define(IS_ACTIVE, false);
    }
}
