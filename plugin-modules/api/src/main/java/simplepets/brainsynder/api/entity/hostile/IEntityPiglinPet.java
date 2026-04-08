package simplepets.brainsynder.api.entity.hostile;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.entity.misc.IShaking;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.PIGLIN)
@VersionLimit(min = {1, 16, 1})
public interface IEntityPiglinPet extends IAgeablePet, IShaking {
    boolean isCharging();

    void setCharging(boolean charging);

    boolean isDancing();

    void setDancing(boolean dancing);
}
