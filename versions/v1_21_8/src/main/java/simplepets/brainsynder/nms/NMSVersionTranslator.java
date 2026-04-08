package simplepets.brainsynder.nms;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.NamespacedKey;
import simplepets.brainsynder.nms.helper.VersionTranslator;

public class NMSVersionTranslator implements VersionTranslator {
    @Override
    public String getVersionIdentifier() {
        return "1.21.8";
    }

    @Override
    public <T> T getRegistryValue (Registry<T> registry, NamespacedKey key) {
        return registry.getValue(ResourceLocation.fromNamespaceAndPath(key.getNamespace(), key.getKey()));
    }
}
