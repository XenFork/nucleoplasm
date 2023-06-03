package union.xenfork.nucleoplasm.json.edit.registry.effect;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
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

    public StatusEffectLoader(StatusEffect effectType) {
        category = StatusEffectCategoryLoader.create(effectType.getCategory());
        color = effectType.getColor();
    }


    public static StatusEffectLoader create(StatusEffect effectType) {
        return new StatusEffectLoader(effectType);
    }

    public StatusEffect getStatusEffect() {
        for (StatusEffect statusEffect : Registries.STATUS_EFFECT) {
            if (statusEffect.getCategory().name().equals(category.getValue())) {
                return statusEffect;
            }
        }
        return null;
    }
}
