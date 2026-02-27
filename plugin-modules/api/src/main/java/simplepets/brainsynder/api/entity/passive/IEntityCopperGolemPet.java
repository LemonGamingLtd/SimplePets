package simplepets.brainsynder.api.entity.passive;

import lib.brainsynder.ServerVersion;
import lib.brainsynder.SupportedVersion;
import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.CopperGolemOxidation;

@EntityPetType(petType = PetType.COPPER_GOLEM)
@SupportedVersion(version = ServerVersion.v1_21_9)
public interface IEntityCopperGolemPet extends IEntityPet {
    CopperGolemOxidation getOxidation ();
    void setOxidation (CopperGolemOxidation wrapper);
}
