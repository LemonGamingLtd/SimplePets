package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.ArmadilloPhase;

@VersionLimit(min = {1, 20, 5})
@EntityPetType(petType = PetType.ARMADILLO)
public interface IEntityArmadilloPet extends IAgeablePet {
    ArmadilloPhase getPhase();

    void setPhase(ArmadilloPhase phase);
}
