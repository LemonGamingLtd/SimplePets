package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.entity.misc.IHorseAbstract;
import simplepets.brainsynder.api.entity.misc.ISitting;
import simplepets.brainsynder.api.entity.misc.ISpecialRiding;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 20, 0})
@EntityPetType(petType = PetType.CAMEL)
public interface IEntityCamelPet extends IHorseAbstract, ISpecialRiding, ISitting {
}
