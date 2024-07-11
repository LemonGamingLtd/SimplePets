package simplepets.brainsynder.api.entity.misc;


import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.wrappers.villager.BiomeType;
import simplepets.brainsynder.api.wrappers.villager.VillagerInfo;
import simplepets.brainsynder.api.wrappers.villager.VillagerLevel;
import simplepets.brainsynder.api.wrappers.villager.VillagerType;

public interface IProfession extends IEntityPet {
    /**
     * This gets used for 1.14+
     */
    default VillagerInfo getVillagerData() {
        return VillagerInfo.getDefault();
    }

    default VillagerType getVillagerType() {
        return getVillagerData().getType();
    }

    default BiomeType getBiome() {
        return getVillagerData().getBiome();
    }

    default VillagerLevel getMasteryLevel() {
        return getVillagerData().getLevel();
    }

    /**
     * This gets used for 1.14+
     */
    default void setVillagerData(VillagerInfo data) {}

    default void setVillagerType(VillagerType type) {
        setVillagerData(getVillagerData().withType(type));
    }

    default void setBiome(BiomeType biome) {
        setVillagerData(getVillagerData().withBiome(biome));
    }

    default void setMasteryLevel(VillagerLevel level) {
        setVillagerData(getVillagerData().withLevel(level));
    }
}
