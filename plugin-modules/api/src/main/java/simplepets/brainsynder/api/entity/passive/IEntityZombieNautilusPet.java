package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.wrappers.ZombieNautilusVariant;

@VersionLimit(min = {1, 21, 11})
@EntityPetType(petType = PetType.ZOMBIE_NAUTILUS)
public interface IEntityZombieNautilusPet extends IEntityNautilusPet {
    ZombieNautilusVariant getVariant();
    void setVariant(ZombieNautilusVariant variant);
}
