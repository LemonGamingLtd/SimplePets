package simplepets.brainsynder.api.entity.misc;

import simplepets.brainsynder.api.entity.IEntityPet;

public interface ISheared extends IEntityPet {
    boolean isSheared();

    void setSheared(boolean sheared);
}
