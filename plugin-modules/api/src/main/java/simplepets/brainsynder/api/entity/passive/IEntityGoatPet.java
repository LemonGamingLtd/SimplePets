package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 17, 0})
@EntityPetType(petType = PetType.GOAT)
public interface IEntityGoatPet extends IAgeablePet {
    /**
     * According to {@link https://minecraft.gamepedia.com/Goat}
     * goats lose a horn each time they ram a block
     * <p>
     * EDIT (6/9/2021): 1.17 does not have a way to change this data yet, hope it is not bedrock only...
     * EDIT (6/7/2022): 1.19 Added the Goat Horn data.
     */
    default void setLeftHorn(boolean hasHorn) {}

    default boolean hasLeftHorn() {
        return true;
    }

    default void setRightHorn(boolean hasHorn) {}

    default boolean hasRightHorn() {
        return true;
    }


    boolean isScreaming();

    void setScreaming(boolean screaming);
}
