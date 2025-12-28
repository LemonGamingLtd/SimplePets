package simplepets.brainsynder.api.entity.passive;


import simplepets.brainsynder.api.entity.misc.*;
import simplepets.brainsynder.api.pet.PetType;

@EntityPetType(petType = PetType.SHEEP)
public interface IEntitySheepPet extends IAgeablePet, IColorable, IRainbow, ISheared {
}
