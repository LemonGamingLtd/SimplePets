package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.entity.misc.ITemperaturePet;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 19, 0})
@EntityPetType(petType = PetType.FROG)
public interface IEntityFrogPet extends IAgeablePet, ITemperaturePet {
    boolean isCroaking();

    void setCroaking(boolean value);

    boolean isUsingTongue();

    void setUsingTongue(boolean value);
}
