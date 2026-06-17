package simplepets.brainsynder.nms.entity.list;

import simplepets.brainsynder.api.entity.passive.IEntityTraderLlamaPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.EntitySelector;

/**
 * NMS: {@link net.minecraft.world.entity.animal.horse.TraderLlama}
 */
public class EntityTraderLlamaPet extends EntityLlamaPet implements IEntityTraderLlamaPet {
    public EntityTraderLlamaPet(PetType type, PetUser user) {
        super(EntitySelector.TRADER_LLAMA, type, user);
    }
}
