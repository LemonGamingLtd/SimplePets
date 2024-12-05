package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import simplepets.brainsynder.api.entity.passive.IEntityWanderingTraderPet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPetOverride;

/**
 * NMS: {@link net.minecraft.world.entity.npc.WanderingTrader}
 */
public class EntityWanderingTraderPet extends EntityPetOverride implements IEntityWanderingTraderPet {
    public EntityWanderingTraderPet(PetType type, PetUser user) {
        super(EntityType.WANDERING_TRADER, type, user);
    }
}
