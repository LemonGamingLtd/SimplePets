package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IFlyableEntity;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 19, 0})
@EntityPetType(petType = PetType.ALLAY)
public interface IEntityAllayPet extends IFlyableEntity {
    boolean isDancing();

    void setDancing(boolean dancing);
}