package simplepets.brainsynder.nms.entity;

import lib.brainsynder.ServerVersion;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.VersionTranslator;
import simplepets.brainsynder.nms.utils.PetDataAccess;
import simplepets.brainsynder.utils.VersionFields;

import java.lang.reflect.Field;

public class EntityBase extends Mob {
    protected  final EntityType<? extends Mob> entityType;
    protected  final EntityType<? extends Mob> originalEntityType;
    private PetUser user;
    private PetType petType;

    protected EntityBase(EntityType<? extends Mob> entitytypes, Level world) {
        super(entitytypes, world);
        entityType = getEntityType(entitytypes);
        originalEntityType = entitytypes;
        VersionTranslator.getBukkitEntity(this).remove();
    }

    public EntityBase(EntityType<? extends Mob> entitytypes, PetType type, PetUser user) {
        super(entitytypes, VersionTranslator.getWorldHandle(user.getPlayer().getLocation().getWorld()));
        this.user = user;
        this.petType = type;
        entityType = getEntityType(entitytypes);
        originalEntityType = entitytypes;
    }

    public void populateDataAccess(PetDataAccess dataAccess) {}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder datawatcher) {
        super.defineSynchedData(datawatcher);

        PetDataAccess dataAccess = new PetDataAccess();
        populateDataAccess(dataAccess);
        dataAccess.getAccessorDefinitions().forEach(datawatcher::define);
    }


    // 1.19.4+   Replaces boolean rideableUnderWater()
    @Override
    public boolean dismountsUnderwater() {
        return false;
    }

    public PetType getPetType() {
        return petType;
    }

    public PetUser getUser() {
        return user;
    }

    EntityType<? extends Mob> getEntityType(EntityType<? extends Mob> originalType)  {
        try {
            Field field = EntityType.class.getDeclaredField(VersionFields.fromServerVersion(ServerVersion.getVersion()).getEntityFactoryField());
            field.setAccessible(true);
            EntityType.Builder<? extends Mob> builder = EntityType.Builder.of((EntityType.EntityFactory<? extends Mob>) field.get(originalType), MobCategory.AMBIENT);
            builder.sized(0.1f, 0.1f);
            return builder.build(petType.name().toLowerCase());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return originalType;
        }
    }

    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }
}
