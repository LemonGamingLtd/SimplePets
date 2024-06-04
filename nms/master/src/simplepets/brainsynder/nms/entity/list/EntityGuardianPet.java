package simplepets.brainsynder.nms.entity.list;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import simplepets.brainsynder.api.entity.hostile.IEntityGuardianPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPet;
import simplepets.brainsynder.nms.utils.PetDataAccess;

/**
 * NMS: {@link net.minecraft.world.entity.monster.Guardian}
 */
public class EntityGuardianPet extends EntityPet implements IEntityGuardianPet {
    private static final EntityDataAccessor<Boolean> MOVING = SynchedEntityData.defineId(EntityGuardianPet.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TARGET_ENTITY = SynchedEntityData.defineId(EntityGuardianPet.class, EntityDataSerializers.INT);

    public EntityGuardianPet(PetType type, PetUser user) {
        super(EntityType.GUARDIAN, type, user);
    }
    public EntityGuardianPet(EntityType<? extends Mob> entitytypes, PetType type, PetUser user) {
        super(entitytypes, type, user);
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(MOVING, Boolean.FALSE);
        dataAccess.define(TARGET_ENTITY, 0);
    }
}
