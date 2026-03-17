package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IEntityFishPet;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 19, 0})
@EntityPetType(petType = PetType.TADPOLE)
public interface IEntityTadpolePet extends IEntityFishPet {
}
