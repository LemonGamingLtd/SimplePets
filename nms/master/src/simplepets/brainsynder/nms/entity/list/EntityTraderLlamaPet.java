package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntityTraderLlamaPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;

/**
 * NMS: {@link net.minecraft.world.entity.animal.horse.TraderLlama}
 */
public class EntityTraderLlamaPet extends EntityLlamaPet implements IEntityTraderLlamaPet {
    public EntityTraderLlamaPet(PetType type, PetUser user) {
        super(EntityType.TRADER_LLAMA, type, user);
    }
}
