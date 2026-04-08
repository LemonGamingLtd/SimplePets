package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.SnifferState;

@VersionLimit(min = {1, 20, 0})
@EntityPetType(petType = PetType.SNIFFER)
public interface IEntitySnifferPet extends IAgeablePet {

    SnifferState getSnifferState();

    void setSnifferState(SnifferState state);

}
