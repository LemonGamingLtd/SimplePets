package simplepets.brainsynder.api.pet.data.color;

import lib.brainsynder.item.ItemBuilder;
import org.bsdevelopment.pluginutils.text.WordUtils;
import org.bukkit.Material;
import simplepets.brainsynder.api.entity.misc.IColorable;
import simplepets.brainsynder.api.pet.PetData;
import simplepets.brainsynder.api.wrappers.DyeColorWrapper;

import java.util.Optional;

public class ColorData extends PetData<IColorable> {
    public ColorData() {
        for (DyeColorWrapper color : DyeColorWrapper.values()) {
            addDefaultItem(color.name(), new ItemBuilder(Material.valueOf(color.name() + "_CONCRETE"))
                .withName(" ")
                .addLore(
                    "&#c8c8c8Previous: {previousColor}{previousName}",
                    "&#c8c8c8Current: {currentColor}{currentName}",
                    "&#c8c8c8Next: {nextColor}{nextName}"));
        }
    }

    @Override
    public String namespace() { return "color"; }

    @Override
    public Object defaultValue() {
        return DyeColorWrapper.WHITE;
    }

    @Override
    public Optional<ItemBuilder> getItem(IColorable entity) {
        Optional<ItemBuilder> optional = super.getItem(entity);
        if (optional.isPresent()) {
            // We have to do this to replace the placholders if there is any

            DyeColorWrapper previous = DyeColorWrapper.getPrevious(entity.getColor());
            DyeColorWrapper next = DyeColorWrapper.getNext(entity.getColor());

            ItemBuilder builder = optional.get();
            builder.replaceString("{previousColor}", previous.getChatColor())
                .replaceString("{currentColor}", entity.getColor().getChatColor())
                .replaceString("{nextColor}", next.getChatColor())
                .replaceString("{previousName}", WordUtils.capitalize(previous.name().toLowerCase().replace("_", " ")))
                .replaceString("{currentName}", WordUtils.capitalize(entity.getColor().name().toLowerCase().replace("_", " ")))
                .replaceString("{nextName}", WordUtils.capitalize(next.name().toLowerCase().replace("_", " ")));
            return Optional.of(builder);
        }
        return optional;
    }

    @Override
    public void onLeftClick(IColorable entity) {
        entity.setColor(DyeColorWrapper.getNext(entity.getColor()));
    }

    @Override
    public void onRightClick(IColorable entity) {
        entity.setColor(DyeColorWrapper.getPrevious(entity.getColor()));
    }

    @Override
    public Object value(IColorable entity) {
        return entity.getColor();
    }
}
