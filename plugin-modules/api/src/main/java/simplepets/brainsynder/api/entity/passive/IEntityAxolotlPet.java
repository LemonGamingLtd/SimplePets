package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.AxolotlVariant;

@VersionLimit(min = {1, 17, 0})
@EntityPetType(petType = PetType.AXOLOTL)
public interface IEntityAxolotlPet extends IAgeablePet {

    boolean isPlayingDead();

    void setPlayingDead(boolean playingDead);

    AxolotlVariant getVariant();

    void setVariant(AxolotlVariant variant);

}
