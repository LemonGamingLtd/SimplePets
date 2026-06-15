package simplepets.brainsynder.nms.entity.list;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import simplepets.brainsynder.api.entity.hostile.IEntitySlimePet;
import simplepets.brainsynder.api.pet.PetType;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.branch.EntityCubeAbstractPet;

/**
 * NMS: {@link net.minecraft.world.entity.monster.cubemob.Slime}
 */
public class EntitySlimePet extends EntityCubeAbstractPet implements IEntitySlimePet {
    public EntitySlimePet(EntityType<? extends Mob> entitytypes, PetType type, PetUser user) {
        super(entitytypes, type, user);
    }
}
