package simplepets.brainsynder.api.entity.passive;

import org.bsdevelopment.pluginutils.version.VersionLimit;



import simplepets.brainsynder.api.entity.misc.EntityPetType;
import simplepets.brainsynder.api.pet.PetType;

@VersionLimit(min = {1, 17, 0})
@EntityPetType(petType = PetType.GLOW_SQUID)
public interface IEntityGlowSquidPet extends IEntitySquidPet {
    boolean isSquidGlowing();

    void setSquidGlowing(boolean glowing);
}
