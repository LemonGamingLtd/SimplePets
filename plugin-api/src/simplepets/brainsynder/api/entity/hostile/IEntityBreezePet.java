package simplepets.brainsynder.api.entity.hostile;


import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.BREEZE)
@SupportedVersion(version = ServerVersion.v1_21)
public interface IEntityBreezePet extends IEntityPet {
}
