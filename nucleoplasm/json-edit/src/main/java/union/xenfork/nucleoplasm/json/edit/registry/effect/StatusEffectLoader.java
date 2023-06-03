package union.xenfork.nucleoplasm.json.edit.registry.effect;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.effect.StatusEffect;
import union.xenfork.nucleoplasm.json.edit.registry.util.StatusEffectCategoryLoader;

/**
 * @author baka4n
 * {@link StatusEffect}
 */
public class StatusEffectLoader {
    @SerializedName("category")
    private StatusEffectCategoryLoader category;
    @SerializedName("color")
    private int color;

    public static StatusEffectLoader create(StatusEffectCategoryLoader category, int color) {
        StatusEffectLoader loader = new StatusEffectLoader();
        loader.category = category;
        loader.color = color;
        return loader;
    }

    public static StatusEffectLoader create(StatusEffect effectType) {
        StatusEffectLoader loader = new StatusEffectLoader();
        loader.category = StatusEffectCategoryLoader.create(effectType.getCategory());
        loader.color = effectType.getColor();
        return loader;
    }
}
