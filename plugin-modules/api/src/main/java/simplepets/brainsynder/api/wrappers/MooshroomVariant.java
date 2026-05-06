package simplepets.brainsynder.api.wrappers;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;

public enum MooshroomVariant implements Iconable {
    RED("d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db"),
    BROWN("8501708e2c00a605a988c419af70c1617ce5688628b7413cfd37038ec0221abc");

    private final String texture;

    MooshroomVariant(String texture) {
        this.texture = "http://textures.minecraft.net/texture/" + texture;
    }

    @Override
    public ItemBuilder getIcon() {
        ItemBuilder builder = ItemBuilder.playerSkull(texture);
        return builder;
    }

    public static MooshroomVariant getByID(int id) {
        for (MooshroomVariant v : values()) {
            if (v.ordinal() == id) {
                return v;
            }
        }
        return RED;
    }

    public static MooshroomVariant getByName(String name) {
        for (MooshroomVariant wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return RED;
    }

    public static MooshroomVariant getPrevious(MooshroomVariant current) {
        if (current == RED) return BROWN;
        return RED;
    }

    public static MooshroomVariant getNext(MooshroomVariant current) {
        if (current == BROWN) return RED;
        return BROWN;
    }
}
