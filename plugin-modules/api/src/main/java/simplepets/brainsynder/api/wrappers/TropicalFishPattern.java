package simplepets.brainsynder.api.wrappers;

import java.util.HashMap;
import java.util.Map;

public enum TropicalFishPattern {
    KOB(0, false),
    SUNSTREAK(1, false),
    SNOOPER(2, false),
    DASHER(3, false),
    BRINELY(4, false),
    SPOTTY(5, false),
    FLOPPER(0, true),
    STRIPEY(1, true),
    GLITTER(2, true),
    BLOCKFISH(3, true),
    BETTY(4, true),
    CLAYFISH(5, true);

    private final int variant;
    private final boolean large;
    private static final Map<Integer, TropicalFishPattern> BY_DATA = new HashMap();

    TropicalFishPattern(int variant, boolean large) {
        this.variant = variant;
        this.large = large;
    }

    public int getDataValue() {
        return this.variant << 8 | (this.large ? 1 : 0);
    }

    public static TropicalFishPattern getByName(String name) {
        for (TropicalFishPattern wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return KOB;
    }

    public static TropicalFishPattern fromData(int data) {
        return BY_DATA.get(data);
    }

    public static TropicalFishPattern getPrevious(TropicalFishPattern current) {
        int target = current.ordinal() - 1;
        if (target < 0) target = (values().length - 1);
        return TropicalFishPattern.values()[target];
    }

    public static TropicalFishPattern getNext(TropicalFishPattern current) {
        int target = current.ordinal() + 1;
        if (target > (values().length - 1)) target = 0;
        return TropicalFishPattern.values()[target];
    }

    static {
        for (TropicalFishPattern type : values()) {
            BY_DATA.put(type.getDataValue(), TropicalFishPattern.values()[type.ordinal()]);
        }
    }
}