/*
 * Copyright 2023
 * BSDevelopment <https://bsdevelopment.org>
 */

package simplepets.brainsynder.utils;

import lib.brainsynder.ServerVersion;
import simplepets.brainsynder.PetCore;

public enum VersionFields implements FieldValues {
    v1_19 (
            "f", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bs", // net.minecraft.world.entity.EntityType$factory
            "ca", // net.minecraft.core.MappedRegistry$frozen
            "cc", // net.minecraft.core.MappedRegistry$intrusiveHolderCache
            "", // Not in this version
            "bn", // net.minecraft.world.entity.LivingEntity$jumping
            "r", // net.minecraft.world.entity.Entity$boardingCooldown
            "Q", // net.minecraft.server.MinecraftServer$running
            "bQ" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_19_1 (
            "f", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bs", // net.minecraft.world.entity.EntityType$factory
            "ca", // net.minecraft.core.MappedRegistry$frozen
            "cc", // net.minecraft.core.MappedRegistry$intrusiveHolderCache
            "", // Not in this version
            "bn", // net.minecraft.world.entity.LivingEntity$jumping
            "r", // net.minecraft.world.entity.Entity$boardingCooldown
            "Q", // net.minecraft.server.MinecraftServer$running
            "bQ" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_19_2 (
            "f", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bs", // net.minecraft.world.entity.EntityType$factory
            "ca", // net.minecraft.core.MappedRegistry$frozen
            "cc", // net.minecraft.core.MappedRegistry$intrusiveHolderCache
            "", // Not in this version
            "bn", // net.minecraft.world.entity.LivingEntity$jumping
            "r", // net.minecraft.world.entity.Entity$boardingCooldown
            "Q", // net.minecraft.server.MinecraftServer$running
            "bQ" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_19_3 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bu", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "h", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bn", // net.minecraft.world.entity.LivingEntity$jumping
            "r", // net.minecraft.world.entity.Entity$boardingCooldown
            "Q", // net.minecraft.server.MinecraftServer$running
            "bQ" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_19_4 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bA", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "h", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bi", // net.minecraft.world.entity.LivingEntity$jumping
            "G", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "bM" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bA", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "h", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bk", // net.minecraft.world.entity.LivingEntity$jumping
            "I", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "bP" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20_1 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bA", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "h", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bk", // net.minecraft.world.entity.LivingEntity$jumping
            "I", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "bP" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20_2 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bA", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "h", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bj", // net.minecraft.world.entity.LivingEntity$jumping
            "J", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "bO" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20_3 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bC", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "g", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bj", // net.minecraft.world.entity.LivingEntity$jumping
            "J", // net.minecraft.world.entity.Entity$boardingCooldown
            "S", // net.minecraft.server.MinecraftServer$running
            "bN" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20_4 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bC", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "g", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bj", // net.minecraft.world.entity.LivingEntity$jumping
            "J", // net.minecraft.world.entity.Entity$boardingCooldown
            "S", // net.minecraft.server.MinecraftServer$running
            "bN" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20_5 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bF", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "g", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bn", // net.minecraft.world.entity.LivingEntity$jumping
            "K", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "bS" // net.minecraft.world.entity.LivingEntity$attributes
    ),
    v1_20_6 (
            "e", // net.minecraft.network.syncher.SynchedEntityData$itemsById
            "bF", // net.minecraft.world.entity.EntityType$factory
            "l", // net.minecraft.core.MappedRegistry$frozen
            "m", // net.minecraft.core.MappedRegistry$unregisteredIntrusiveHolders
            "g", // net.minecraft.core.registries.BuiltInRegistries#ENTITY_TYPE
            "bn", // net.minecraft.world.entity.LivingEntity$jumping
            "K", // net.minecraft.world.entity.Entity$boardingCooldown
            "R", // net.minecraft.server.MinecraftServer$running
            "bS" // net.minecraft.world.entity.LivingEntity$attributes
    );

    public static VersionFields fromServerVersion (ServerVersion version) {
        return valueOf(version.name());
    }


    private final boolean mojangMapped;
    private final FieldName entityDataMap, entityFactory, registryFrozen, registryIntrusive, entityRegistry, entityJump, resetCooldown, isRunning, attributes;

    VersionFields (String entityDataMap, String entityFactory, String registryFrozen, String registryIntrusive,
                   String entityRegistry, String entityJump,String resetCooldown, String isRunning, String attributes) {
        this.entityDataMap = new FieldName ("itemsById", entityDataMap);
        this.entityFactory = new FieldName ("factory", entityFactory);
        this.registryFrozen = new FieldName ("frozen", registryFrozen);
        this.registryIntrusive = new FieldName ("unregisteredIntrusiveHolders", registryIntrusive);
        this.entityRegistry = new FieldName ("ENTITY_TYPE", entityRegistry);
        this.entityJump = new FieldName ("jumping", entityJump);
        this.resetCooldown = new FieldName ("boardingCooldown", resetCooldown);
        this.isRunning = new FieldName ("running", isRunning);
        this.attributes = new FieldName ("attributes", attributes);

        mojangMapped = PetCore.SERVER_INFORMATION.isMojangMapped();
    }

    @Override
    public String getEntityDataMapField() {
        return mojangMapped ? entityDataMap.mojangMapped() : entityDataMap.obfuscated();
    }

    @Override
    public String getEntityFactoryField() {
        return mojangMapped ? entityFactory.mojangMapped() : entityFactory.obfuscated();
    }

    @Override
    public String getRegistryFrozenField() {
        return mojangMapped ? registryFrozen.mojangMapped() : registryFrozen.obfuscated();
    }

    @Override
    public String getRegistryIntrusiveField() {
        return mojangMapped ? registryIntrusive.mojangMapped() : registryIntrusive.obfuscated();
    }

    @Override
    public String getEntityRegistryField() {
        return mojangMapped ? entityRegistry.mojangMapped() : entityRegistry.obfuscated();
    }

    @Override
    public String getEntityJumpField() {
        return mojangMapped ? entityJump.mojangMapped() : entityJump.obfuscated();
    }

    @Override
    public String getRideCooldownField() {
        return mojangMapped ? resetCooldown.mojangMapped() : resetCooldown.obfuscated();
    }

    @Override
    public String getServerRunningField() {
        return mojangMapped ? isRunning.mojangMapped() : isRunning.obfuscated();
    }

    public String getAttributesField() {
        return mojangMapped ? attributes.mojangMapped() : attributes.obfuscated();
    }

    public record FieldName (String mojangMapped, String obfuscated) {}
}
