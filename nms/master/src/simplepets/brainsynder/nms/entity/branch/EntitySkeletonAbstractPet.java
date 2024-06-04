package simplepets.brainsynder.nms.entity.branch;

import lib.brainsynder.nbt.StorageTagCompound;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import simplepets.brainsynder.api.entity.misc.ISkeletonAbstract;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityAgeablePet;
import simplepets.brainsynder.nms.utils.PetDataAccess;

public class EntitySkeletonAbstractPet extends EntityAgeablePet implements ISkeletonAbstract {
    private static final EntityDataAccessor<Boolean> SWINGING_ARMS = SynchedEntityData.defineId(EntitySkeletonAbstractPet.class, EntityDataSerializers.BOOLEAN);

    public EntitySkeletonAbstractPet(EntityType<? extends Mob> entitytypes, PetType type, PetUser user) {
        super(entitytypes, type, user);
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(SWINGING_ARMS, false);
    }

    @Override
    public boolean isArmsRaised() {
        return entityData.get(SWINGING_ARMS);
    }

    @Override
    public void setArmsRaised(boolean flag) {
        entityData.set(SWINGING_ARMS, flag);
    }

    @Override
    public StorageTagCompound asCompound() {
        StorageTagCompound object = super.asCompound();
        object.setBoolean("raised", isArmsRaised());
        return object;
    }

    @Override
    public void applyCompound(StorageTagCompound object) {
        if (object.hasKey("raised")) setArmsRaised(object.getBoolean("raised"));
        super.applyCompound(object);
    }
}