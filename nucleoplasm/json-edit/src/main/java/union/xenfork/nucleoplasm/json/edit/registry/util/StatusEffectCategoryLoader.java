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

    public static StatusEffectCategoryLoader create(String value, FormattingLoader formattingLoader) {
        StatusEffectCategoryLoader loader = new StatusEffectCategoryLoader();
        loader.value = value;
        loader.loader = formattingLoader;
        return loader;
    }

    public static StatusEffectCategoryLoader create(StatusEffectCategory category) {
        StatusEffectCategoryLoader loader = new StatusEffectCategoryLoader();
        loader.value = category.name();
        loader.loader = FormattingLoader.create(category.getFormatting());
        return loader;
    }
}
