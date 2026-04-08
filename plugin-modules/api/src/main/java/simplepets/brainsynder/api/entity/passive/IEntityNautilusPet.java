package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.ISaddle;
import simplepets.brainsynder.api.entity.misc.ITameable;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.NautilusArmorType;

@VersionLimit(min = {1, 21, 11})
@EntityPetType(petType = PetType.NAUTILUS)
public interface IEntityNautilusPet extends ITameable, ISaddle {
    void setArmor (NautilusArmorType armor);

    NautilusArmorType getArmor();
}
