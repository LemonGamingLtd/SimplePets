package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.entity.misc.ISaddle;
import simplepets.brainsynder.api.pet.PetType;


@EntityPetType(petType = PetType.SPIDER)
@VersionLimit(min = {1, 16, 1})
public interface IEntityStriderPet extends IAgeablePet, ISaddle {
    boolean isCold();

    void setCold(boolean cold);
}
