package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 21, 11})
@EntityPetType(petType = PetType.CAMEL_HUSK)
public interface IEntityCamelHuskPet extends IEntityCamelPet {
}
