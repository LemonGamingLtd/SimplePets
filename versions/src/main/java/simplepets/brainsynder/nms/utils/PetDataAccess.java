package simplepets.brainsynder.nms.utils;

import net.minecraft.network.syncher.EntityDataAccessor;

import java.util.LinkedHashMap;

public class PetDataAccess {
    private final LinkedHashMap<EntityDataAccessor, Object> data = new LinkedHashMap();

    public void define (EntityDataAccessor<?> accessor, Object value) {
        data.put(accessor, value);
    }

    public LinkedHashMap<EntityDataAccessor, Object> getAccessorDefinitions() {
        return data;
    }
}
