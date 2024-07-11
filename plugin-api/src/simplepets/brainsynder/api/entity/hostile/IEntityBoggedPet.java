package simplepets.brainsynder.api.entity.hostile;

import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.ISheared;
import simplepets.brainsynder.api.entity.misc.ISkeletonAbstract;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.BOGGED)
@SupportedVersion(version = ServerVersion.v1_21)
public interface IEntityBoggedPet extends ISkeletonAbstract, ISheared {
}
