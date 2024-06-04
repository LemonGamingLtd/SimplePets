package simplepets.brainsynder.api.entity.passive;

import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IFlyableEntity;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.BAT)
public interface IEntityBatPet extends IEntityPet, IFlyableEntity {
    boolean isHanging();

    void setHanging(boolean var1);
}