package union.xenfork.nucleoplasm.json.edit.registry.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * @author baka4n
 * {@link StatusEffectCategory}
 */
public class StatusEffectCategoryLoader {
    @SerializedName("formatting_value")
    private String value;
    @SerializedName("formatting")
    private FormattingLoader loader;

    public StatusEffectCategoryLoader(StatusEffectCategory category) {
        value = category.name();
        loader = FormattingLoader.create(category.getFormatting());
    }

    public static StatusEffectCategoryLoader create(StatusEffectCategory category) {
        return new StatusEffectCategoryLoader(category);
    }

    public String getValue() {
        return value;
    }

    public FormattingLoader getLoader() {
        return loader;
    }

    public StatusEffectCategory getStatusEffecctCategory() {
        for (StatusEffectCategory statusEffectCategory : StatusEffectCategory.values()) {
            if (value.equals(statusEffectCategory.name())) {
                return statusEffectCategory;
            }
        }
        return null;
    }
}
