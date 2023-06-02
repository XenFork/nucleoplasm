package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item.Settings;

/**
 * @author baka4n
 * {@link Settings}
 */
public class SettingsLoader {
    @SerializedName("rarity")
    private String rarity_class_name;
    @SerializedName("recipeRemainder")
    private ItemLoader loader;
    @SerializedName("maxDamage")
    private int max_damage;

    @SerializedName("maxCount")
    private int max_count;

    @SerializedName("foodComponent")
    private FoodComponentLoader foodComponent;
}
