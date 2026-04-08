package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.CopperGolemOxidation;

@EntityPetType(petType = PetType.COPPER_GOLEM)
@VersionLimit(min = {1, 21, 9})
public interface IEntityCopperGolemPet extends IEntityPet {
    CopperGolemOxidation getOxidation ();
    void setOxidation (CopperGolemOxidation wrapper);
}
