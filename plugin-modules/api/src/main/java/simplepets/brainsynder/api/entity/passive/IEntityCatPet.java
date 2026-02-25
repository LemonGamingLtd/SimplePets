package simplepets.brainsynder.api.entity.passive;

import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IColorable;
import simplepets.brainsynder.api.entity.misc.ISleeper;
import simplepets.brainsynder.api.entity.misc.ITameable;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.CatType;

@EntityPetType(petType = PetType.CAT)
public interface IEntityCatPet extends ITameable, ISleeper, IColorable {
    CatType getCatType();

    void setCatType(CatType type);

    boolean isHeadUp();

    void setHeadUp(boolean value);
}
