package simplepets.brainsynder.api.wrappers;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;

public enum RabbitVariant implements Iconable {
    BROWN("7d1169b2694a6aba826360992365bcda5a10c89a3aa2b48c438531dd8685c3a7"),
    WHITE("374d8298797e712bb1f75ad6ffa7734ac4237ea69be1db92f0e41115a2c170cf"),
    BLACK("72c58116a147d1a9a26269224a8be184fe8e5f3f3df9b61751369ad87382ec9"),
    BLACK_AND_WHITE("cb8cff4b15b8ca37e25750f345718f289cb22c5b3ad22627a71223faccc"),
    GOLD("c977a3266bf3b9eaf17e5a02ea5fbb46801159863dd288b93e6c12c9cb"),
    SALT_AND_PEPPER("ffecc6b5e6ea5ced74c46e7627be3f0826327fba26386c6cc7863372e9bc"),
    THE_KILLER_BUNNY("1f59851de93f4c6547f809ca3aed189e94bbf4f888f1f75208e94c3733852a1");

    private final String texture;

    RabbitVariant(String texture) {
        this.texture = "http://textures.minecraft.net/texture/" + texture;
    }

    @Override
    public ItemBuilder getIcon() {
        return ItemBuilder.playerSkull(texture);
    }

    public static RabbitVariant getByID(int id) {
        if (id == 99) return THE_KILLER_BUNNY;
        for (RabbitVariant v : values()) {
            if (v.ordinal() == id) return v;
        }
        return null;
    }

    public static RabbitVariant getByName(String name) {
        for (RabbitVariant wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return BROWN;
    }

    public static RabbitVariant getPrevious(RabbitVariant current) {
        return (current == BROWN) ? THE_KILLER_BUNNY : values()[current.ordinal() - 1];
    }

    public static RabbitVariant getNext(RabbitVariant current) {
        return (current == THE_KILLER_BUNNY) ? BROWN : values()[current.ordinal() + 1];
    }

    public int getId() {
        if (this == THE_KILLER_BUNNY) return 99;
        return ordinal();
    }
}
