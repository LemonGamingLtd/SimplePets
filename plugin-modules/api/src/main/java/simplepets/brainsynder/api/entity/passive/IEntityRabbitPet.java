package simplepets.brainsynder.api.entity.passive;

import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.RabbitVariant;

@EntityPetType(petType = PetType.RABBIT)
public interface IEntityRabbitPet extends IAgeablePet {
    RabbitVariant getRabbitType();

    void setRabbitType(RabbitVariant type);
}
