package simplepets.brainsynder.nms.pathfinder;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.Goal;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.nms.entity.EntityPet;
import simplepets.brainsynder.nms.helper.VersionHelper;
import simplepets.brainsynder.nms.utils.EntityUtils;

import java.util.EnumSet;

public class PathfinderGoalLookAtOwner extends Goal {
    private final EntityPet entityPet;
    private PetUser user;
    private Player player;
    private final float range;
    private int lookTime;
    private final float chance;

    public PathfinderGoalLookAtOwner(EntityPet entityPet, float range, float chance) {
        this.entityPet = entityPet;
        this.range = range;
        this.chance = chance;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        this.user = entityPet.getPetUser();
        if (user == null) return false;
        this.player = user.getPlayer();
        if (player == null || !player.isOnline() || player.isDead()) return false;
        if (!player.getWorld().equals(entityPet.getBukkitEntity().getWorld())) return false;
        if (entityPet.distanceToSqr(VersionHelper.<ServerPlayer>getEntityHandle(player)) > range * range) return false;
        if (user.isPetHat(entityPet.getPetType())) return false;

        if (EntityUtils.getRandom().nextFloat() >= this.chance) {
            return false;
        } else {
            return this.user != null;
        }
    }

    // Translation: shouldContinue
    @Override
    public boolean canContinueToUse() {
        if (this.player == null || !this.player.isOnline() || this.player.isDead()
                || !player.getWorld().equals(entityPet.getBukkitEntity().getWorld())
                || user.isPetHat(entityPet.getPetType())) {
            return false;
        } else if (this.entityPet.distanceToSqr(VersionHelper.<ServerPlayer>getEntityHandle(player)) > (double)(this.range * this.range)) {
            return false;
        } else {
            return this.lookTime > 0;
        }
    }

    // Translation: start
    @Override
    public void start() {
        this.lookTime = 40 + EntityUtils.getRandom().nextInt(40);
    }

    // Translation: tick
    @Override
    public void tick() {
        Location location = player.getEyeLocation();

        this.entityPet.getLookControl().setLookAt(location.getX(), location.getY(), location.getZ());
        --this.lookTime;
    }
}
