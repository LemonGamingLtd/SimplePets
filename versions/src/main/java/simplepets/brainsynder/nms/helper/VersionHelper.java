package simplepets.brainsynder.nms.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.Vec3;
import org.bsdevelopment.nbt.StorageTagCompound;
import org.bsdevelopment.pluginutils.reflection.FieldAccessor;
import org.bsdevelopment.pluginutils.utilities.NBTCodec;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import simplepets.brainsynder.api.entity.misc.IFlyableEntity;
import simplepets.brainsynder.api.plugin.utils.HelperUtilities;
import simplepets.brainsynder.nms.entity.EntityPet;
import simplepets.brainsynder.nms.utils.InvalidInputException;
import simplepets.brainsynder.utils.VersionFields;

public class VersionHelper {
    public static final VersionTranslator VERSION_TRANSLATOR = HelperUtilities.getVersionedClass(
            "NMSVersionTranslator",
            VersionTranslator.class,
            DefaultVersionTranslator.class);

    private static final FieldAccessor<AttributeMap> accessor = FieldAccessor.getField(
            LivingEntity.class,
            VersionFields.current().getAttributesField(),
            AttributeMap.class);

    public static BlockPos getPosition (Entity entity) {
        return BlockPos.containing(new Vec3(entity.getX(), entity.getY(), entity.getZ()));
    }

    public static Level getEntityLevel (Entity entity) {
        return entity.level();
    }
    public static boolean isWalkable (EntityPet entity, BlockPos.MutableBlockPos blockposition) {
        return WalkNodeEvaluator.getPathTypeStatic(entity, blockposition) == PathType.WALKABLE;
    }

    public static void overrideAttributeMap (EntityPet entityPet) {
        accessor.set(entityPet, new AttributeMap(createAttributes(entityPet).build()));
    }

    private static AttributeSupplier.Builder createAttributes (EntityPet entityPet) {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        if (entityPet instanceof IFlyableEntity) builder.add(Attributes.FLYING_SPEED, 1);
        builder.add(Attributes.SCALE, 1);
        builder.add(Attributes.STEP_HEIGHT, 1);
        return builder.add(Attributes.MOVEMENT_SPEED, 1);
    }

    public static void killEntity (Entity entity, ServerLevel level) {
        entity.kill(level);
    }

    public static ClientboundTeleportEntityPacket getTeleportPacket (Entity entity) {
        return new ClientboundTeleportEntityPacket(entity.getId(), PositionMoveRotation.of(entity), Relative.ALL, entity.onGround);
    }

    public static void moveTo (Entity entityPet, double x, double y, double z, float yaw, float pitch) {
        entityPet.snapTo(x, y, z, yaw, pitch);
    }

    public static void setFollowAttributes (EntityPet entityPet, double value) {
        entityPet.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(value);
    }

    public static void setItemSlot(ArmorStand stand, EquipmentSlot enumitemslot, ItemStack itemstack, boolean silent) {
        stand.setItemSlot(enumitemslot, itemstack, silent);
    }

    public static boolean addEntity (Level level, Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        return level.addFreshEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    public static <T extends Entity> T getEntityHandle(org.bukkit.entity.Entity entity) {
        return (T) ((CraftEntity) entity).getHandle();
    }

    public static org.bukkit.inventory.ItemStack toItemStack(StorageTagCompound compound) {
        if (!compound.hasKey("id")) return new org.bukkit.inventory.ItemStack(Material.AIR);
        if (!compound.hasKey("Count")) compound.setByte("Count", (byte) 1);
        try {
            return NBTCodec.nbtStringToBukkit(compound.toString());
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new InvalidInputException("Failed to parse Item NBT", e);
        }
    }

    public static StorageTagCompound fromItemStack(org.bukkit.inventory.ItemStack item) {
        try {
            return NBTCodec.bukkitToStorageTag(item);
        } catch (IllegalStateException e) {
            throw new InvalidInputException("Failed to convert item to NBT", e);
        }
    }

    public static void setAttributes (EntityPet entityPet, double walkSpeed, double flySpeed) {
        if (walkSpeed != -1) entityPet.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(walkSpeed);
        if ((flySpeed != -1) && (entityPet instanceof IFlyableEntity) && entityPet.getAttribute(Attributes.FLYING_SPEED) != null) {
            entityPet.getAttribute(Attributes.FLYING_SPEED).setBaseValue(flySpeed);
        }
    }
}
