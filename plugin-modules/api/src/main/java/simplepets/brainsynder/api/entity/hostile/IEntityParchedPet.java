package simplepets.brainsynder.api.entity.hostile;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 21, 11})
@EntityPetType(petType = PetType.PARCHED)
public interface IEntityParchedPet extends IEntitySkeletonPet {
}
