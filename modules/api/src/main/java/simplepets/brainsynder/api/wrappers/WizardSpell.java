package simplepets.brainsynder.api.wrappers;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bukkit.Material;

public enum WizardSpell {
    NONE(0.0D, 0.0D, 0.0D, ItemBuilder.of(Material.BARRIER)),
    SUMMON_VEX(0.7D, 0.7D, 0.8D, ItemBuilder.of(Material.GRAY_DYE)),
    FANGS(0.4D, 0.3D, 0.35D, ItemBuilder.of(Material.BROWN_DYE)),
    WOLOLO(0.7D, 0.5D, 0.2D, ItemBuilder.of(Material.ORANGE_DYE)),
    DISAPPEAR(0.3D, 0.3D, 0.8D, ItemBuilder.of(Material.LIGHT_BLUE_DYE)),
    BLINDNESS(0.1D, 0.1D, 0.2D, ItemBuilder.of(Material.BLACK_DYE));

    private final double[] array;
    private final ItemBuilder builder;

    WizardSpell(double var4, double var8, double var6, ItemBuilder builder) {
        this.builder = builder;
        this.array = new double[]{var4, var6, var8};
    }

    public ItemBuilder getIcon() {
        return this.builder;
    }

    public static WizardSpell getByName(String name) {
        for (WizardSpell wrapper : values()) {
            if (wrapper.name().equalsIgnoreCase(name)) return wrapper;
        }
        return NONE;
    }

    public double[] getArray() {
        return array;
    }
}