package simplepets.brainsynder.api.pet.data.tropicalfish;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bsdevelopment.pluginutils.text.WordUtils;
import simplepets.brainsynder.api.entity.passive.IEntityTropicalFishPet;
import simplepets.brainsynder.api.pet.PetData;
import simplepets.brainsynder.api.wrappers.DyeColorWrapper;
import simplepets.brainsynder.api.wrappers.TropicalFishPattern;

import java.util.Optional;

public class PatternData extends PetData<IEntityTropicalFishPet> {
    public PatternData() {
        for (TropicalFishPattern pattern : TropicalFishPattern.values()) {
            addDefaultItem(pattern.name(), ItemBuilder.playerSkull("http://textures.minecraft.net/texture/36d149e4d499929672e2768949e6477959c21e65254613b327b538df1e4df")
                .withName("&#c8c8c8{name}: &a" + pattern.name()));
        }
    }

    @Override
    public String namespace() { return "pattern"; }

    @Override
    public Object defaultValue() {
        return TropicalFishPattern.KOB;
    }

    @Override
    public Optional<ItemBuilder> getItem(IEntityTropicalFishPet entity) {
        Optional<ItemBuilder> optional = super.getItem(entity);
        if (optional.isPresent()) {
            // We have to do this to replace the placholders if there is any

            DyeColorWrapper previous = DyeColorWrapper.getPrevious(entity.getPatternColor());
            DyeColorWrapper next = DyeColorWrapper.getNext(entity.getPatternColor());

            ItemBuilder builder = optional.get();
            builder.replaceString("{previousColor}", previous.getChatColor())
                .replaceString("{currentColor}", entity.getPatternColor().getChatColor())
                .replaceString("{nextColor}", next.getChatColor())
                .replaceString("{previousName}", WordUtils.capitalize(previous.name().toLowerCase().replace("_", " ")))
                .replaceString("{currentName}", WordUtils.capitalize(entity.getPatternColor().name().toLowerCase().replace("_", " ")))
                .replaceString("{nextName}", WordUtils.capitalize(next.name().toLowerCase().replace("_", " ")));

            builder.replaceString("{previousColor}", previous.getChatColor())
                .replaceString("{currentColor}", entity.getPatternColor().getChatColor())
                .replaceString("{nextColor}", next.getChatColor())
                .replaceString("{previousName}", WordUtils.capitalize(previous.name().toLowerCase().replace("_", " ")))
                .replaceString("{currentName}", WordUtils.capitalize(entity.getPatternColor().name().toLowerCase().replace("_", " ")))
                .replaceString("{nextName}", WordUtils.capitalize(next.name().toLowerCase().replace("_", " ")));
            return Optional.of(builder);
        }
        return optional;
    }

    @Override
    public void onLeftClick(IEntityTropicalFishPet entity) {
        entity.setPattern(TropicalFishPattern.getNext(entity.getPattern()));
    }

    @Override
    public void onRightClick(IEntityTropicalFishPet entity) {
        entity.setPattern(TropicalFishPattern.getPrevious(entity.getPattern()));
    }

    @Override
    public Object value(IEntityTropicalFishPet entity) {
        return entity.getPattern();
    }
}
