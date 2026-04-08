package simplepets.brainsynder.api.entity.hostile;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.WardenAnger;

@VersionLimit(min = {1, 19, 0})
@EntityPetType(petType = PetType.WARDEN)
public interface IEntityWardenPet extends IEntityPet {

    void setAngerLevel (WardenAnger level);
    WardenAnger getAngerLevel ();

    void setVibrationEffect (boolean value);
    boolean getVibrationEffect ();
}
