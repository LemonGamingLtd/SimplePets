package simplepets.brainsynder.nms.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.lang.reflect.Field;

public interface VersionTranslator {
    String getVersionIdentifier();

    default Packet<ClientGamePacketListener> getAddEntityPacket(LivingEntity livingEntity, ServerEntity serverEntity, EntityType<?> originalEntityType, BlockPos pos) {
        Packet<ClientGamePacketListener> packet;
        try {
            // y'all here sum'n?
            packet = new ClientboundAddEntityPacket(livingEntity, serverEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ClientboundAddEntityPacket(livingEntity, 0, pos);
        }

        try {
            // TODO: Refactor this mess, as it still uses the obfuscated field name
            Field type = packet.getClass().getDeclaredField(getEntityTypeVariable());
            type.setAccessible(true);
            type.set(packet, useInteger() ? BuiltInRegistries.ENTITY_TYPE.getId(originalEntityType) : originalEntityType);
            return packet;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ClientboundAddEntityPacket(livingEntity, 0, pos);
    }
    private String getEntityTypeVariable() {
        return "c";
    }
    private boolean useInteger() {
        return true;
    }
}
