package simplepets.brainsynder.api.entity.hostile;

import simplepets.brainsynder.api.entity.IEntityPet;
import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IFlyableEntity;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.GHAST)
public interface IEntityGhastPet extends IEntityPet, IFlyableEntity {
    @Override
    default boolean isBig() {
        return true;
    }

    boolean isScreaming();

    void setScreaming(boolean var);
}
