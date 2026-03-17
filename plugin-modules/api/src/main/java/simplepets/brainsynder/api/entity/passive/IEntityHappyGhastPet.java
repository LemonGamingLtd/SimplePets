package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IAgeablePet;
import simplepets.brainsynder.api.entity.misc.IFlyableEntity;
import simplepets.brainsynder.api.entity.misc.IResetColor;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 21, 6})
@EntityPetType(petType = PetType.HAPPY_GHAST)
public interface IEntityHappyGhastPet extends IAgeablePet, IResetColor, IFlyableEntity {}
