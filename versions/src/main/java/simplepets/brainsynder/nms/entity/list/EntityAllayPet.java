package simplepets.brainsynder.nms.entity.list;

import lib.brainsynder.nbt.StorageTagCompound;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import org.bsdevelopment.pluginutils.libs.json.JsonObject;
import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.passive.IEntityAllayPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityFlyablePet;
import simplepets.brainsynder.nms.utils.PetDataAccess;

/**
 * NMS: {@link net.minecraft.world.entity.animal.allay.Allay }
 */
@VersionLimit(min = {1, 19, 0})
public class EntityAllayPet extends EntityFlyablePet implements IEntityAllayPet {
    private static final EntityDataAccessor<Boolean> DATA_DANCING = SynchedEntityData.defineId(EntityAllayPet.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CAN_DUPLICATE = SynchedEntityData.defineId(EntityAllayPet.class, EntityDataSerializers.BOOLEAN);

    public EntityAllayPet(PetType type, PetUser user) {
        super(EntityType.ALLAY, type, user);
    }

    @Override
    public void populateDataAccess(PetDataAccess dataAccess) {
        super.populateDataAccess(dataAccess);
        dataAccess.define(DATA_DANCING, false);
        dataAccess.define(DATA_CAN_DUPLICATE, false);
    }

    @Override
    public boolean isDancing() {
        return entityData.get(DATA_DANCING);
    }

    @Override
    public void setDancing(boolean dancing) {
        entityData.set(DATA_DANCING, dancing);
    }

    @Override
    public void fetchPetData(JsonObject data) {
        super.fetchPetData(data);
        data.add("dancing", isDancing());
    }

    @Override
    public StorageTagCompound asCompound() {
        StorageTagCompound compound = super.asCompound();
        compound.setBoolean("dancing", isDancing());
        return compound;
    }

    @Override
    public void applyCompound(StorageTagCompound object) {
        if (object.hasKey("dancing")) setDancing(object.getBoolean("dancing", false));
        super.applyCompound(object);
    }
}
