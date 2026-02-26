package simplepets.brainsynder.api.wrappers;

import lib.brainsynder.item.ItemBuilder;
import org.bukkit.Material;

public enum AxolotlVariant implements Iconable {
    LUCY("3b83a38a458c3cca0761e2c8210c6f5d2f3380e860d50d2f4756516a2642617d"),
    WILD("4d7efe02012cf31ae2708e7d7df079726575c7ee8504328175fe544708187dce"),
    GOLD("7f80cc1492e44668cccdb40178c3a6689e8dfc0d234e98553fb7debc26fcaeac"),
    CYAN("e1c2d0c3b96ad45b466388e028b247aafe36b26b12c411ecb72e9b50ea21e52c"),
    BLUE("300e28cd4dde9af7a99aeb12c235ff1d3bf6d7e966263bd91a7118b3dfeabcb6");

    private final String texture;

    AxolotlVariant(String texture) {
        this.texture = "http://textures.minecraft.net/texture/" + texture;
    }

    @Override
    public ItemBuilder getIcon() {
        return new ItemBuilder(Material.PLAYER_HEAD).setTexture(texture);
    }

    public static AxolotlVariant getPrevious(AxolotlVariant current) {
        return (current == LUCY) ? BLUE : values()[current.ordinal() - 1];
    }

    public static AxolotlVariant getNext(AxolotlVariant current) {
        return (current == BLUE) ? LUCY : values()[current.ordinal() + 1];
    }
}
