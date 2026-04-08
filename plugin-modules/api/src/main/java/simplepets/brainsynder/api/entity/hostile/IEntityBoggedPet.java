package simplepets.brainsynder.api.entity.hostile;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.ISheared;
import simplepets.brainsynder.api.entity.misc.ISkeletonAbstract;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.BOGGED)
@VersionLimit(min = {1, 21, 0})
public interface IEntityBoggedPet extends ISkeletonAbstract, ISheared {
}
