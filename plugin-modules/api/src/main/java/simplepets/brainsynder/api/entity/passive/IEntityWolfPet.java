package simplepets.brainsynder.api.entity.passive;


import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IColorable;
import simplepets.brainsynder.api.entity.misc.IShaking;
import simplepets.brainsynder.api.entity.misc.ITameable;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.WolfVariant;

@EntityPetType(petType = PetType.WOLF)
public interface IEntityWolfPet extends ITameable, IColorable, IShaking {

    boolean isHeadTilted();

    void setHeadTilted(boolean var);

    boolean isAngry();

    void setAngry(boolean var);

    default WolfVariant getWolfType() {
        return WolfVariant.PALE;
    }

    default void setWolfType(WolfVariant type) {}
}
