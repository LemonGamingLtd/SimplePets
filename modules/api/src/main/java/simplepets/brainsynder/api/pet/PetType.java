package simplepets.brainsynder.api.pet;

import com.google.common.collect.Lists;
import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bsdevelopment.pluginutils.sound.SafeSound;
import org.bsdevelopment.pluginutils.text.Colorize;
import org.bsdevelopment.pluginutils.text.WordUtils;
import org.bsdevelopment.pluginutils.version.VersionCompatibility;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.ambient.IEntityArmorStandPet;
import simplepets.brainsynder.api.entity.hostile.*;
import simplepets.brainsynder.api.entity.passive.*;
import simplepets.brainsynder.api.pet.annotations.ControlledMob;
import simplepets.brainsynder.api.pet.annotations.InDevelopment;
import simplepets.brainsynder.api.pet.annotations.LargePet;
import simplepets.brainsynder.api.pet.data.*;
import simplepets.brainsynder.api.pet.data.bee.BeeAngryData;
import simplepets.brainsynder.api.pet.data.bee.FlippedData;
import simplepets.brainsynder.api.pet.data.bee.NectarData;
import simplepets.brainsynder.api.pet.data.bee.StingerData;
import simplepets.brainsynder.api.pet.data.color.ColorData;
import simplepets.brainsynder.api.pet.data.color.RainbowData;
import simplepets.brainsynder.api.pet.data.color.ResetColorData;
import simplepets.brainsynder.api.pet.data.fox.FoxCrouchingData;
import simplepets.brainsynder.api.pet.data.fox.FoxInterestData;
import simplepets.brainsynder.api.pet.data.fox.FoxSittingData;
import simplepets.brainsynder.api.pet.data.fox.FoxTypeData;
import simplepets.brainsynder.api.pet.data.frog.CroakingData;
import simplepets.brainsynder.api.pet.data.frog.TongueData;
import simplepets.brainsynder.api.pet.data.goat.LeftHornData;
import simplepets.brainsynder.api.pet.data.goat.RightHornData;
import simplepets.brainsynder.api.pet.data.horse.ChestData;
import simplepets.brainsynder.api.pet.data.horse.HorseArmorData;
import simplepets.brainsynder.api.pet.data.horse.HorseColorData;
import simplepets.brainsynder.api.pet.data.horse.HorseStyleData;
import simplepets.brainsynder.api.pet.data.panda.PandaSittingData;
import simplepets.brainsynder.api.pet.data.panda.PandaSleepData;
import simplepets.brainsynder.api.pet.data.panda.PandaSneezeData;
import simplepets.brainsynder.api.pet.data.panda.PandaTypeData;
import simplepets.brainsynder.api.pet.data.temperature.TemperatureVariantData;
import simplepets.brainsynder.api.pet.data.villager.VillagerBiomeData;
import simplepets.brainsynder.api.pet.data.villager.VillagerLevelData;
import simplepets.brainsynder.api.pet.data.villager.VillagerTypeData;
import simplepets.brainsynder.api.pet.data.warden.WardenAngerData;
import simplepets.brainsynder.api.pet.data.warden.WardenVibrationData;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.debug.DebugLevel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public enum PetType {
    UNKNOWN(ItemBuilder.of(Material.STONE)),

    ALLAY(IEntityAllayPet.class, "40e1c7064af7dee68677efaa95f6e6e01430b006dd91638ea2a61849254488ec",
            SafeSound.of(Sound.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM)),

    ARMADILLO(IEntityArmadilloPet.class, "9164ed0e0ef69b0ce7815e4300b4413a4828fcb0092918543545a418a48e0c3c",
            SafeSound.of(Sound.ENTITY_ARMADILLO_AMBIENT),
            AgeData.class, ArmadilloPhaseData.class),

    @ControlledMob
    ARMOR_STAND(IEntityArmorStandPet.class, Material.ARMOR_STAND,
            SafeSound.of(Sound.ENTITY_ARMOR_STAND_FALL)),

    AXOLOTL(IEntityAxolotlPet.class, "5c138f401c67fc2e1e387d9c90a9691772ee486e8ddbf2ed375fc8348746f936",
            SafeSound.of(Sound.ENTITY_AXOLOTL_IDLE_WATER),
            AgeData.class,
            AxolotlVariantData.class,
            AxolotlPlayDeadData.class),

    BAT(IEntityBatPet.class, "9e99deef919db66ac2bd28d6302756ccd57c7f8b12b9dca8f41c3e0a04ac1cc",
            SafeSound.of(Sound.ENTITY_BAT_AMBIENT),
            BatHangData.class),

    BEE(IEntityBeePet.class, "fa2cb74c13245d3ce9bacc8b1600af02fd7c91f501feaf97364e1f8b6f04f47f",
            SafeSound.of(Sound.ENTITY_BEE_LOOP),
            AgeData.class, BeeAngryData.class, NectarData.class, StingerData.class, FlippedData.class),

    BLAZE(IEntityBlazePet.class, "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0",
            SafeSound.of(Sound.ENTITY_BLAZE_AMBIENT)),

    BREEZE(IEntityBreezePet.class, "a275728af7e6a29c88125b675a39d88ae9919bb61fdc200337fed6ab0c49d65c",
            SafeSound.of(Sound.ENTITY_BREEZE_IDLE_GROUND)),

    BOGGED(IEntityBoggedPet.class, "a3b9003ba2d05562c75119b8a62185c67130e9282f7acbac4bc2824c21eb95d9",
            SafeSound.of(Sound.ENTITY_BOGGED_AMBIENT),
            ShearData.class),

    CAMEL(IEntityCamelPet.class, "92b31239520511ca7b6712ef0ecfb55b6c56b9347240f4cbf9925ce0bf0fa445",
            SafeSound.of(Sound.ENTITY_CAMEL_AMBIENT),
            AgeData.class, SittingData.class),

    // TODO: Need to update the ambient sound for the CAMEL_HUSK
    CAMEL_HUSK(IEntityCamelHuskPet.class, "3bd7a92a6f67b7500d16c4e12f28058ec2859311556ba03be2d1f581170f2db6",
            SafeSound.of("", "ENTITY_GENERIC_EXPLODE"),
            AgeData.class, SittingData.class),

    CAT(IEntityCatPet.class, "6b253fc6b656988453a2d7138fca4d1f2752f47691f0c434e432183771cfe1",
            SafeSound.of(Sound.ENTITY_CAT_AMBIENT),
            AgeData.class, TamedData.class, SittingData.class, SleepData.class, CatCollarData.class, CatTypeData.class, CatTiltData.class),

    CAVE_SPIDER(IEntityCaveSpiderPet.class, "5617f7dd5ed16f3bd186440517cd440a170015b1cc6fcb2e993c05de33f",
            SafeSound.of(Sound.ENTITY_SPIDER_AMBIENT)),

    CHICKEN(IEntityChickenPet.class, "1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893",
            SafeSound.of(Sound.ENTITY_CHICKEN_AMBIENT),
            AgeData.class, TemperatureVariantData.ChickenTemperature.class),

    COD(IEntityCodPet.class, "7892d7dd6aadf35f86da27fb63da4edda211df96d2829f691462a4fb1cab0",
            SafeSound.of(Sound.ENTITY_COD_AMBIENT)),

    COW(IEntityCowPet.class, "c5a9cd58d4c67bccc8fb1f5f756a2d381c9ffac2924b7f4cb71aa9fa13fb5c",
            SafeSound.of(Sound.ENTITY_COW_AMBIENT),
            AgeData.class, TemperatureVariantData.CowTemperature.class),

    CREEPER(IEntityCreeperPet.class, Material.CREEPER_HEAD,
            SafeSound.of(Sound.ENTITY_CREEPER_HURT),
            PoweredData.class),

    CREAKING(IEntityCreakingPet.class, "77b5be72769ccff1a6cb77c5848e01d7e5704a3d349c0737ff93cb54d02380ac",
            SafeSound.of(Sound.ENTITY_CREAKING_AMBIENT)),

    COPPER_GOLEM(IEntityCopperGolemPet.class, "99e24e94dbe42e230d83293a77d61ff7101a8c68ab68bbc6a93f9630fb2fdb4",
            SafeSound.of("ENTITY_COPPER_GOLEM_STEP"),
            OxidationData.class),

    DOLPHIN(IEntityDolphinPet.class, "8e9688b950d880b55b7aa2cfcd76e5a0fa94aac6d16f78e833f7443ea29fed3",
            SafeSound.of(Sound.ENTITY_DOLPHIN_AMBIENT),
            AgeData.class),

    DONKEY(IEntityDonkeyPet.class, "399bb50d1a214c394917e25bb3f2e20698bf98ca703e4cc08b42462df309d6e6",
            SafeSound.of(Sound.ENTITY_DONKEY_AMBIENT),
            AgeData.class, ChestData.class, EatingData.class, SaddleData.class),

    DROWNED(IEntityDrownedPet.class, "c3f7ccf61dbc3f9fe9a6333cde0c0e14399eb2eea71d34cf223b3ace22051",
            SafeSound.of(Sound.ENTITY_DROWNED_AMBIENT),
            AgeData.class, ArmsData.class, ShakeData.class),

    @LargePet
    ELDER_GUARDIAN(IEntityElderGuardianPet.class, "1c797482a14bfcb877257cb2cff1b6e6a8b8413336ffb4c29a6139278b436b",
            SafeSound.of(Sound.ENTITY_ELDER_GUARDIAN_AMBIENT)),

    ENDERMAN(IEntityEndermanPet.class, "96c0b36d53fff69a49c7d6f3932f2b0fe948e032226d5e8045ec58408a36e951",
            SafeSound.of(Sound.ENTITY_ENDERMAN_AMBIENT),
            EndermanScreamData.class),

    ENDERMITE(IEntityEndermitePet.class, "5bc7b9d36fb92b6bf292be73d32c6c5b0ecc25b44323a541fae1f1e67e393a3e",
            SafeSound.of(Sound.ENTITY_ENDERMITE_AMBIENT)),

    EVOKER(IEntityEvokerPet.class, "d954135dc82213978db478778ae1213591b93d228d36dd54f1ea1da48e7cba6",
            SafeSound.of(Sound.ENTITY_EVOKER_AMBIENT),
            SpellData.class),

    FOX(IEntityFoxPet.class, "d8954a42e69e0881ae6d24d4281459c144a0d5a968aed35d6d3d73a3c65d26a",
            SafeSound.of(Sound.ENTITY_FOX_AMBIENT),
            AgeData.class, SleepData.class, FoxInterestData.class, FoxCrouchingData.class, FoxTypeData.class, FoxSittingData.class),

    FROG(IEntityFrogPet.class, "23ce6f9998ed2da757d1e6372f04efa20e57dfc17c3a06478657bbdf51c2f2a2",
            SafeSound.of(Sound.ENTITY_FROG_AMBIENT),
            TemperatureVariantData.FrogTemperature.class, CroakingData.class, TongueData.class),

    @LargePet
    GHAST(IEntityGhastPet.class, "7a8b714d32d7f6cf8b37e221b758b9c599ff76667c7cd45bbc49c5ef19858646",
            SafeSound.of(Sound.ENTITY_GHAST_AMBIENT),
            GhastScreamData.class),

    @LargePet
    GIANT(IEntityGiantPet.class, Material.ZOMBIE_HEAD,
            SafeSound.of(Sound.ENTITY_ZOMBIE_AMBIENT)),

    GLOW_SQUID(IEntityGlowSquidPet.class, "3e94a1bb1cb00aaa153a74daf4b0eea20b8974522fe9901eb55aef478ebeff0d",
            SafeSound.of(Sound.ENTITY_GLOW_SQUID_AMBIENT),
            AgeData.class, GlowingData.class),

    GOAT(IEntityGoatPet.class, "957607099d06b7a8b1327093cd0a488be7c9f50b6121b22151271b59170f3c21",
            SafeSound.of(Sound.ENTITY_GOAT_AMBIENT),
            AgeData.class, LeftHornData.class, RightHornData.class),

    GUARDIAN(IEntityGuardianPet.class, "a0bf34a71e7715b6ba52d5dd1bae5cb85f773dc9b0d457b4bfc5f9dd3cc7c94",
            SafeSound.of(Sound.ENTITY_GUARDIAN_AMBIENT)),

    @LargePet
    HAPPY_GHAST(IEntityHappyGhastPet.class, "a1a36cb93d01675c4622dd5c8d872110911ec12c372e89afa8ba03862867f6fb",
            SafeSound.of(Sound.ENTITY_HAPPY_GHAST_AMBIENT),
            AgeData.class, ResetColorData.class),

    @LargePet
    HOGLIN(IEntityHoglinPet.class, "9bb9bc0f01dbd762a08d9e77c08069ed7c95364aa30ca1072208561b730e8d75",
            SafeSound.of(Sound.ENTITY_HOGLIN_AMBIENT),
            AgeData.class, ShakeData.class),

    HORSE(IEntityHorsePet.class, "628d1ab4be1e28b7b461fdea46381ac363a7e5c3591c9e5d2683fbe1ec9fcd3",
            SafeSound.of(Sound.ENTITY_HORSE_AMBIENT),
            AgeData.class, EatingData.class, HorseArmorData.class, HorseColorData.class, HorseStyleData.class, SaddleData.class),

    HUSK(IEntityHuskPet.class, "d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121",
            SafeSound.of(Sound.ENTITY_HUSK_AMBIENT),
            AgeData.class, ArmsData.class, ShakeData.class),

    ILLUSIONER(IEntityIllusionerPet.class, "512512e7d016a2343a7bff1a4cd15357ab851579f1389bd4e3a24cbeb88b",
            SafeSound.of(Sound.ENTITY_ILLUSIONER_AMBIENT),
            SpellData.class),

    IRON_GOLEM(IEntityIronGolemPet.class, "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714",
            SafeSound.of(Sound.ENTITY_IRON_GOLEM_STEP)),

    LLAMA(IEntityLlamaPet.class, "818cd457fbaf327fa39f10b5b36166fd018264036865164c02d9e5ff53f45",
            SafeSound.of(Sound.ENTITY_LLAMA_AMBIENT),
            AgeData.class, ChestData.class, ResetColorData.class, LlamaSkinData.class, SaddleData.class),

    MAGMA_CUBE(IEntityMagmaCubePet.class, "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429",
            SafeSound.of(Sound.ENTITY_MAGMA_CUBE_SQUISH),
            SizeData.class),

    MOOSHROOM(IEntityMooshroomPet.class, "2b52841f2fd589e0bc84cbabf9e1c27cb70cac98f8d6b3dd065e55a4dcb70d77",
            SafeSound.of(Sound.ENTITY_COW_AMBIENT),
            AgeData.class, MooshroomColorData.class),

    MULE(IEntityMulePet.class, "46dcda265e57e4f51b145aacbf5b59bdc6099ffd3cce0a661b2c0065d80930d8",
            SafeSound.of(Sound.ENTITY_MULE_AMBIENT),
            AgeData.class, ChestData.class, EatingData.class, SaddleData.class),

    // TODO: Need to update the ambient sound for the NAUTILUS
    NAUTILUS(IEntityNautilusPet.class, "3bb340dd3302615348de5162fe1670b9c5c9c616cd92d2de9d8398cb33e842ae",
            SafeSound.of("", "ENTITY_GENERIC_EXPLODE"),
            AgeData.class, SaddleData.class, NautilusArmorData.class),

    OCELOT(IEntityOcelotPet.class, "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1",
            SafeSound.of(Sound.ENTITY_OCELOT_AMBIENT),
            AgeData.class),

    PANDA(IEntityPandaPet.class, "dca096eea506301bea6d4b17ee1605625a6f5082c71f74a639cc940439f47166",
            SafeSound.of(Sound.ENTITY_PANDA_AMBIENT),
            AgeData.class, PandaTypeData.class, PandaSittingData.class, PandaSleepData.class, PandaSneezeData.class),

    // TODO: Need to update the ambient sound for the PARCHED
    PARCHED(IEntityParchedPet.class, "24aeceff5f26dd8413c5c03547c234ac03108d187af0b9cd834a8ce12598591c",
            SafeSound.of("", "ENTITY_GENERIC_EXPLODE")),

    PARROT(IEntityParrotPet.class, "a4ba8d66fecb1992e94b8687d6ab4a5320ab7594ac194a2615ed4df818edbc3",
            SafeSound.of(Sound.ENTITY_PARROT_AMBIENT),
            RainbowData.class, TamedData.class, ParrotColorData.class, SittingData.class),

    PHANTOM(IEntityPhantomPet.class, "746830da5f83a3aaed838a99156ad781a789cfcf13e25beef7f54a86e4fa4",
            SafeSound.of(Sound.ENTITY_PHANTOM_AMBIENT),
            SizeData.class),

    PIG(IEntityPigPet.class, "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4",
            SafeSound.of(Sound.ENTITY_PIG_AMBIENT),
            AgeData.class, SaddleData.class, TemperatureVariantData.PigTemperature.class),

    PIGLIN(IEntityPiglinPet.class, "9f18107d275f1cb3a9f973e5928d5879fa40328ff3258054db6dd3e7c0ca6330",
            SafeSound.of(Sound.ENTITY_PIGLIN_AMBIENT),
            AgeData.class, ChargingData.class, DancingData.class, ShakeData.class),

    PIGLIN_BRUTE(IEntityPiglinBrutePet.class, "3e300e9027349c4907497438bac29e3a4c87a848c50b34c21242727b57f4e1cf",
            SafeSound.of(Sound.ENTITY_PIGLIN_BRUTE_AMBIENT),
            ShakeData.class),

    PILLAGER(IEntityPillagerPet.class, "4aee6bb37cbfc92b0d86db5ada4790c64ff4468d68b84942fde04405e8ef5333",
            SafeSound.of(Sound.ENTITY_PILLAGER_AMBIENT)),

    POLARBEAR(IEntityPolarBearPet.class, "c4fe926922fbb406f343b34a10bb98992cee4410137d3f88099427b22de3ab90",
            SafeSound.of(Sound.ENTITY_POLAR_BEAR_AMBIENT),
            AgeData.class, StandingData.class),

    PUFFERFISH(IEntityPufferFishPet.class, "17152876bc3a96dd2a2299245edb3beef647c8a56ac8853a687c3e7b5d8bb",
            SafeSound.of(Sound.ENTITY_PUFFER_FISH_STING),
            PufferSizeData.class),

    RABBIT(IEntityRabbitPet.class, "ffecc6b5e6ea5ced74c46e7627be3f0826327fba26386c6cc7863372e9bc",
            SafeSound.of(Sound.ENTITY_RABBIT_AMBIENT),
            AgeData.class, RabbitColorData.class),

    @LargePet
    RAVAGER(IEntityRavagerPet.class, "cd20bf52ec390a0799299184fc678bf84cf732bb1bd78fd1c4b441858f0235a8",
            SafeSound.of(Sound.ENTITY_RAVAGER_AMBIENT),
            RavagerChompData.class),

    SALMON(IEntitySalmonPet.class, "8aeb21a25e46806ce8537fbd6668281cf176ceafe95af90e94a5fd84924878",
            SafeSound.of(Sound.ENTITY_SALMON_AMBIENT)),

    SHEEP(IEntitySheepPet.class, "f31f9ccc6b3e32ecf13b8a11ac29cd33d18c95fc73db8a66c5d657ccb8be70",
            SafeSound.of(Sound.ENTITY_SHEEP_AMBIENT),
            AgeData.class, ColorData.class, RainbowData.class, ShearData.class),

    @ControlledMob
    SHULKER(IEntityShulkerPet.class, "1433a4b73273a64c8ab2830b0fff777a61a488c92f60f83bfb3e421f428a44",
            SafeSound.of(Sound.ENTITY_SHULKER_AMBIENT),
            ResetColorData.class, RainbowData.class, ShulkerCloseData.class),

    SILVERFISH(IEntitySilverfishPet.class, "d06310a8952b265c6e6bed4348239ddea8e5482c8c68be6fff981ba8056bf2e",
            SafeSound.of(Sound.ENTITY_SILVERFISH_AMBIENT)),

    SKELETON(IEntitySkeletonPet.class, Material.SKELETON_SKULL,
            SafeSound.of(Sound.ENTITY_SKELETON_AMBIENT)),

    SKELETON_HORSE(IEntitySkeletonHorsePet.class, "47effce35132c86ff72bcae77dfbb1d22587e94df3cbc2570ed17cf8973a",
            SafeSound.of(Sound.ENTITY_SKELETON_HORSE_AMBIENT),
            AgeData.class, EatingData.class, SaddleData.class),

    SLIME(IEntitySlimePet.class, "bb13133a8fb4ef00b71ef9bab639a66fbc7d5cffcc190c1df74bf2161dfd3ec7",
            SafeSound.of(Sound.ENTITY_SLIME_SQUISH), SizeData.class),

    @LargePet
    SNIFFER(IEntitySnifferPet.class, "87ad920a66e38cc3426a5bff084667e8772116915e298098567c139f222e2c42",
            SafeSound.of(Sound.ENTITY_SNIFFER_IDLE),
            AgeData.class, SnifferStateData.class),

    SNOWMAN(IEntitySnowmanPet.class, "9aed9fe4ed0893e325f4fbd32b093c1cc562cba27ff73359d356f1c288e441f9",
            SafeSound.of(Sound.ENTITY_SNOW_GOLEM_AMBIENT),
            PumpkinData.class),

    SPIDER(IEntitySpiderPet.class, "c87a96a8c23b83b32a73df051f6b84c2ef24d25ba4190dbe74f11138629b5aef",
            SafeSound.of(Sound.ENTITY_SPIDER_AMBIENT)),

    SQUID(IEntitySquidPet.class, "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac",
            SafeSound.of(Sound.ENTITY_SQUID_AMBIENT),
            AgeData.class),

    STRAY(IEntityStrayPet.class, "2c5097916bc0565d30601c0eebfeb287277a34e867b4ea43c63819d53e89ede7",
            SafeSound.of(Sound.ENTITY_STRAY_AMBIENT)),

    STRIDER(IEntityStriderPet.class, "cb7ffdda656c68d88851a8e05b48cd2493773ffc4ab7d64e9302229fe3571059",
            SafeSound.of(Sound.ENTITY_STRIDER_AMBIENT),
            AgeData.class, SaddleData.class),

    TADPOLE(IEntityTadpolePet.class, "987035f5352334c2cba6ac4c65c2b9059739d6d0e839c1dd98d75d2e77957847",
            SafeSound.of(Sound.ENTITY_TADPOLE_FLOP)),

    TRADER_LLAMA(IEntityTraderLlamaPet.class, "8424780b3c5c5351cf49fb5bf41fcb289491df6c430683c84d7846188db4f84d",
            SafeSound.of(Sound.ENTITY_LLAMA_AMBIENT),
            AgeData.class, ChestData.class, ResetColorData.class, LlamaSkinData.class),

    TROPICAL_FISH(IEntityTropicalFishPet.class, "d6dd5e6addb56acbc694ea4ba5923b1b25688178feffa72290299e2505c97281",
            SafeSound.of(Sound.ENTITY_TROPICAL_FISH_AMBIENT),
            TropicalBodyColorData.class, TropicalPatternData.class, TropicalPatternColorData.class),

    TURTLE(IEntityTurtlePet.class, "0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a",
            SafeSound.of(Sound.ENTITY_TURTLE_SHAMBLE),
            AgeData.class),

    VEX(IEntityVexPet.class, "c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd",
            SafeSound.of(Sound.ENTITY_VEX_AMBIENT),
            PoweredData.class),

    VILLAGER(IEntityVillagerPet.class, "41b830eb4082acec836bc835e40a11282bb51193315f91184337e8d3555583",
            SafeSound.of(Sound.ENTITY_VILLAGER_AMBIENT),
            AgeData.class, ShakeData.class, VillagerTypeData.class, VillagerBiomeData.class, VillagerLevelData.class),

    VINDICATOR(IEntityVindicatorPet.class, "6deaec344ab095b48cead7527f7dee61b063ff791f76a8fa76642c8676e2173",
            SafeSound.of(Sound.ENTITY_VINDICATOR_AMBIENT),
            JohnnyData.class),

    WANDERING_TRADER(IEntityWanderingTraderPet.class, "5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932",
            SafeSound.of(Sound.ENTITY_WANDERING_TRADER_AMBIENT)),

    @LargePet
    WARDEN(IEntityWardenPet.class, "1dfd13ca08bf973bfef0293d770704a11ef5a9fe20d40671fb066724d3e18d8",
            SafeSound.of(Sound.ENTITY_WARDEN_AMBIENT),
            WardenAngerData.class, WardenVibrationData.class),

    WITCH(IEntityWitchPet.class, "20e13d18474fc94ed55aeb7069566e4687d773dac16f4c3f8722fc95bf9f2dfa",
            SafeSound.of(Sound.ENTITY_WITCH_AMBIENT),
            PotionData.class),

    @LargePet
    WITHER(IEntityWitherPet.class, "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f",
            SafeSound.of(Sound.ENTITY_WITHER_AMBIENT),
            WitherShieldData.class, WitherSizeData.class),

    WITHER_SKELETON(IEntityWitherSkeletonPet.class, Material.WITHER_SKELETON_SKULL,
            SafeSound.of(Sound.ENTITY_WITHER_SKELETON_AMBIENT)),

    WOLF(IEntityWolfPet.class, "24d7727f52354d24a64bd6602a0ce71a7b484d05963da83b470360faa9ceab5f",
            SafeSound.of(Sound.ENTITY_WOLF_AMBIENT),
            AgeData.class, TamedData.class, AngryData.class, ColorData.class, SittingData.class,
            WolfTiltData.class, ShakeData.class, WolfTypeData.class),

    @LargePet
    ZOGLIN(IEntityZoglinPet.class, "3c8c7c5d0556cd6629716e39188b21e7c0477479f242587bf19e0bc76b322551",
            SafeSound.of(Sound.ENTITY_ZOGLIN_AMBIENT),
            AgeData.class),

    ZOMBIE(IEntityZombiePet.class, Material.ZOMBIE_HEAD,
            SafeSound.of(Sound.ENTITY_ZOMBIE_AMBIENT),
            AgeData.class, ArmsData.class, ShakeData.class),

    ZOMBIE_HORSE(IEntityZombieHorsePet.class, "d22950f2d3efddb18de86f8f55ac518dce73f12a6e0f8636d551d8eb480ceec",
            SafeSound.of(Sound.ENTITY_ZOMBIE_HORSE_AMBIENT),
            AgeData.class, EatingData.class, SaddleData.class),

    // TODO: Need to update the ambient sound for the ZOMBIE_NAUTILUS
    // TODO: Need to add the pet customization (EG: variant warm/temperate)
    ZOMBIE_NAUTILUS(IEntityZombieNautilusPet.class, "fd9a933376da44c3391307cb9f4cf03f16f3a54f495fd5a11bad8a373f9d5720",
            SafeSound.of("", "ENTITY_GENERIC_EXPLODE"),
            AgeData.class, SaddleData.class, NautilusArmorData.class),

    ZOMBIE_VILLAGER(IEntityZombieVillagerPet.class, "e5e08a8776c1764c3fe6a6ddd412dfcb87f41331dad479ac96c21df4bf3ac89c",
            SafeSound.of(Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT),
            AgeData.class, ArmsData.class, ShakeData.class, VillagerTypeData.class, VillagerBiomeData.class, VillagerLevelData.class),

    ZOMBIFIED_PIGLIN(IEntityPigZombiePet.class, "7eabaecc5fae5a8a49c8863ff4831aaa284198f1a2398890c765e0a8de18da8c",
            SafeSound.of(Sound.ENTITY_ZOMBIFIED_PIGLIN_AMBIENT),
            AgeData.class, ArmsData.class);

    private final ItemBuilder builder;
    private final Class<? extends IEntityPet> entityClass;
    private final List<PetData> petData = Lists.newArrayList();

    PetType(ItemBuilder builder) {
        this(null, builder, SafeSound.of(Sound.BLOCK_STONE_BUTTON_CLICK_ON));
    }

    PetType(Class<? extends IEntityPet> entityClass, Material material, SafeSound sound) {
        this(entityClass, ItemBuilder.of(material), sound);
    }

    PetType(Class<? extends IEntityPet> entityClass, String textureID, SafeSound sound) {
        this(entityClass, ItemBuilder.playerSkull("http://textures.minecraft.net/texture/" + textureID), sound);
    }

    @SafeVarargs
    PetType(Class<? extends IEntityPet> entityClass, Material material, SafeSound sound, Class<? extends PetData>... petData) {
        this(entityClass, ItemBuilder.of(material), sound, petData);
    }

    @SafeVarargs
    PetType(Class<? extends IEntityPet> entityClass, String textureID, SafeSound sound, Class<? extends PetData>... petData) {
        this(entityClass, ItemBuilder.playerSkull("http://textures.minecraft.net/texture/" + textureID), sound, petData);
    }

    @SafeVarargs
    PetType(Class<? extends IEntityPet> entityClass, ItemBuilder builder, SafeSound sound, Class<? extends PetData>... petData) {
        this.entityClass = entityClass;
        LinkedList<Class<? extends PetData>> list = Lists.newLinkedList();
        list.addFirst(SilentData.class);
        list.addFirst(BurningData.class);
        list.addFirst(FrozenData.class);
        list.addFirst(Visible.class);
        Arrays.asList(petData).forEach(dataClass -> {
            if (VersionCompatibility.isCompatible(dataClass)) list.add(dataClass);
        });
        this.builder = builder.withName(Colorize.translateBungeeHex("&#c8f792" + WordUtils.capitalize(name().toLowerCase().replace("_", " "))));

        list.forEach(clazz -> {
            try {
                this.petData.add(clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Failed to create an instance of " + clazz.getSimpleName());
                SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Error: " + e.getMessage());
            }
        });
    }

    public static Optional<PetType> getPetType(String name) {
        try {
            return Optional.of(valueOf(PetType.class, name.toUpperCase().trim()));
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

    public String getPermission() {
        return "pet.type." + name().toLowerCase().replace("_", "");
    }

    public String getPermission(String addition) {
        return getPermission() + "." + addition;
    }

    public List<PetData> getPetData() {
        return petData;
    }

    public ItemBuilder getBuilder() {
        return builder;
    }

    public Class<? extends IEntityPet> getEntityClass() {
        return entityClass;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public EntityType getEntityType() {
        return switch (this) {
            case POLARBEAR -> EntityType.POLAR_BEAR;
            case SNOWMAN -> EntityType.SNOW_GOLEM;
            default -> EntityType.valueOf(name());
        };
    }

    public boolean isSupported() {
        return VersionCompatibility.isCompatible(this) && VersionCompatibility.isCompatible(entityClass);
    }

    public boolean isLargePet() {
        try {
            return getClass().getField(this.name()).getAnnotation(LargePet.class) != null;
        } catch (NoSuchFieldException ignored) {
        }
        return false;
    }

    public boolean isInDevelopment() {
        try {
            return getClass().getField(this.name()).getAnnotation(InDevelopment.class) != null;
        } catch (NoSuchFieldException ignored) {
        }
        return false;
    }
}
