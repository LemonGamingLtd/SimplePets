package simplepets.brainsynder.nms.entity.list;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.hostile.IEntitySulfurCubePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.branch.EntityCubeAbstractPet;
import simplepets.brainsynder.nms.utils.PetDataAccess;

/**
 * NMS: {@link net.minecraft.world.entity.monster.cubemob.SulfurCube}
 */
public class EntitySulfurCubePet extends EntityCubeAbstractPet implements IEntitySulfurCubePet {
    private static final EntityDataAccessor<Integer> MAX_FUSE = SynchedEntityData.defineId(EntitySulfurCubePet.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntitySulfurCubePet.class, EntityDataSerializers.BOOLEAN);

    public EntitySulfurCubePet(PetType type, PetUser user) {
        super(EntityType.MAGMA_CUBE, type, user); // TODO: Change EntityType to SULFUR_CUDE
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(FROM_BUCKET, false);
        dataAccess.define(MAX_FUSE, -1);
    }
}
