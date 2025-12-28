package simplepets.brainsynder.api.entity.passive;

import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.ArmadilloPhase;

@SupportedVersion(version = ServerVersion.v1_20_5)
@EntityPetType(petType = PetType.ARMADILLO)
public interface IEntityArmadilloPet extends IAgeablePet {
    ArmadilloPhase getPhase();

    void setPhase(ArmadilloPhase phase);
}
