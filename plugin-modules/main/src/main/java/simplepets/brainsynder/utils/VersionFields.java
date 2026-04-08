/*
 * Copyright 2023
 * BSDevelopment <https://bsdevelopment.org>
 */

package simplepets.brainsynder.utils;

import org.bsdevelopment.pluginutils.version.ServerVersion;
import simplepets.brainsynder.PetCore;

import java.util.HashMap;
import java.util.Map;

public enum VersionFields implements FieldValues {
    // Minecraft 1.21.11
    V1_21_11(
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "cj", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "g", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bM", // net.minecraft.world.entity.LivingEntity$jumping
            "X", // net.minecraft.world.entity.Entity$boardingCooldown
            "S", // net.minecraft.server.MinecraftServer$running
            "cm", // net.minecraft.world.entity.LivingEntity$attributes
            "d" // net.minecraft.network.protocol.game.ClientboundAddEntityPacket$type
    ),

    // Minecraft 1.21.9
    V1_21_9(
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "cf", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "g", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bL", // net.minecraft.world.entity.LivingEntity$jumping
            "X", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "cj", // net.minecraft.world.entity.LivingEntity$attributes
            "d" // net.minecraft.network.protocol.game.ClientboundAddEntityPacket$type
    ),

    // Minecraft 1.21.6 – 1.21.8
    V1_21_6(
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "cc", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "f", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bB", // net.minecraft.world.entity.LivingEntity$jumping
            "W", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "cc", // net.minecraft.world.entity.LivingEntity$attributes
            "f" // net.minecraft.network.protocol.game.ClientboundAddEntityPacket$type
    );

    /**
     * Builds the lookup map for all supported {@link ServerVersion} values.
     */
    private static Map<ServerVersion, VersionFields> buildVersionMap() {
        Map<ServerVersion, VersionFields> map = new HashMap<>();
        // 1.21.x
        map.put(ServerVersion.v1_21_6, V1_21_6);
        map.put(ServerVersion.v1_21_7, V1_21_6);
        map.put(ServerVersion.v1_21_8, V1_21_6);
        map.put(ServerVersion.v1_21_9, V1_21_9);
        map.put(ServerVersion.v1_21_10, V1_21_9);
        map.put(ServerVersion.v1_21_11, V1_21_11);
        return map;
    }


    public static VersionFields fromServerVersion(ServerVersion version) {
        VersionFields fields = VERSION_MAP.get(version);
        if (fields == null) throw new IllegalArgumentException("Unsupported server version: " + version);

        return fields;
    }

    //--------------------------------------------------------------------------
    // Mojang‐mapped (deobfuscated) field names
    //--------------------------------------------------------------------------
    private static final String MOJANG_ENTITY_DATA_MAP = "itemsById";
    private static final String MOJANG_ENTITY_FACTORY = "factory";
    private static final String MOJANG_REGISTRY_FROZEN = "frozen";
    private static final String MOJANG_REGISTRY_INTRUSIVE = "unregisteredIntrusiveHolders";
    private static final String MOJANG_ENTITY_REGISTRY = "ENTITY_TYPE";
    private static final String MOJANG_ENTITY_JUMP = "jumping";
    private static final String MOJANG_RIDE_COOLDOWN = "boardingCooldown";
    private static final String MOJANG_SERVER_RUNNING = "running";
    private static final String MOJANG_ATTRIBUTES = "attributes";
    private static final String MOJANG_TYPE = "type";


    private static final Map<ServerVersion, VersionFields> VERSION_MAP = buildVersionMap();
    private static final VersionFields CURRENT = fromServerVersion(ServerVersion.getVersion());
    private static final boolean MOJANG_MAPPED = PetCore.SERVER_INFORMATION.isMojangMapped();

    private final FieldName entityDataMap, entityFactory, registryFrozen, registryIntrusive, entityRegistry, entityJump, resetCooldown, isRunning, attributes, type;

    VersionFields(String entityDataMap, String entityFactory, String registryFrozen, String registryIntrusive,
                  String entityRegistry, String entityJump, String resetCooldown, String isRunning, String attributes, String type) {
        this.entityDataMap = new FieldName(MOJANG_ENTITY_DATA_MAP, entityDataMap);
        this.entityFactory = new FieldName(MOJANG_ENTITY_FACTORY, entityFactory);
        this.registryFrozen = new FieldName(MOJANG_REGISTRY_FROZEN, registryFrozen);
        this.registryIntrusive = new FieldName(MOJANG_REGISTRY_INTRUSIVE, registryIntrusive);
        this.entityRegistry = new FieldName(MOJANG_ENTITY_REGISTRY, entityRegistry);
        this.entityJump = new FieldName(MOJANG_ENTITY_JUMP, entityJump);
        this.resetCooldown = new FieldName(MOJANG_RIDE_COOLDOWN, resetCooldown);
        this.isRunning = new FieldName(MOJANG_SERVER_RUNNING, isRunning);
        this.attributes = new FieldName(MOJANG_ATTRIBUTES, attributes);
        this.type = new FieldName(MOJANG_TYPE, type);
    }

    public static VersionFields current() {
        return CURRENT;
    }

    /**
     * Chooses between Mojang‐mapped and obfuscated names based on the cached flag.
     *
     * @param fieldName pair of names
     * @return the correct field name for the current runtime
     */
    private String resolve(FieldName fieldName) {
        return MOJANG_MAPPED ? fieldName.mojangMapped() : fieldName.obfuscated();
    }

    @Override
    public String getEntityDataMapField() {
        return resolve(entityDataMap);
    }

    @Override
    public String getEntityFactoryField() {
        return resolve(entityFactory);
    }

    @Override
    public String getRegistryFrozenField() {
        return resolve(registryFrozen);
    }

    @Override
    public String getRegistryIntrusiveField() {
        return resolve(registryIntrusive);
    }

    @Override
    public String getEntityRegistryField() {
        return resolve(entityRegistry);
    }

    @Override
    public String getEntityJumpField() {
        return resolve(entityJump);
    }

    @Override
    public String getRideCooldownField() {
        return resolve(resetCooldown);
    }

    @Override
    public String getServerRunningField() {
        return resolve(isRunning);
    }

    public String getAttributesField() {return resolve(attributes);}

    public String getPacketEntityType() {
        return resolve(type);
    }

    public record FieldName(String mojangMapped, String obfuscated) {}
}
