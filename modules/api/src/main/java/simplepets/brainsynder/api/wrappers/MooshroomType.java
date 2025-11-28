package simplepets.brainsynder.api.wrappers;

public enum MooshroomType {
    RED("d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db"),
    BROWN("8501708e2c00a605a988c419af70c1617ce5688628b7413cfd37038ec0221abc");

    private final String texture;

    MooshroomType(String texture) {
        this.texture = "http://textures.minecraft.net/texture/" + texture;
    }

    public String getTexture() {
        return texture;
    }

    public static MooshroomType getByName(String name) {
        for (MooshroomType wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return RED;
    }
}
