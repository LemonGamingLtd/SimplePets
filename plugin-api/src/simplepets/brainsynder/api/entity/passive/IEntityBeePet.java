package simplepets.brainsynder.api.entity.passive;

import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.entity.misc.IFlyableEntity;
import simplepets.brainsynder.api.entity.misc.ISpecialFlag;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.BEE)
public interface IEntityBeePet extends IAgeablePet, ISpecialFlag, IFlyableEntity {
    boolean isAngry();

    void setAngry(boolean angry);

    default boolean hasNectar() {
        return getSpecialFlag(8);
    }

    default void setHasNectar(boolean hasNectar) {
        setSpecialFlag(8, hasNectar);
    }

    default boolean hasStung() {
        return getSpecialFlag(4);
    }

    default void setHasStung(boolean hasStung) {
        setSpecialFlag(4, hasStung);
    }

    default boolean isFlipped() {
        return getSpecialFlag(2);
    }

    default void setFlipped(boolean flipped) {
        setSpecialFlag(2, flipped);
    }
}
