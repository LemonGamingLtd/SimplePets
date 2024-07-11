package simplepets.brainsynder.api.entity.passive;

import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.FrogType;

@SupportedVersion(version = ServerVersion.v1_19)
@EntityPetType(petType = PetType.FROG)
public interface IEntityFrogPet extends IAgeablePet {
    void setVariant(FrogType variant);

    FrogType getVariant();

    boolean isCroaking();

    void setCroaking(boolean value);

    boolean isUsingTongue();

    void setUsingTongue(boolean value);
}
