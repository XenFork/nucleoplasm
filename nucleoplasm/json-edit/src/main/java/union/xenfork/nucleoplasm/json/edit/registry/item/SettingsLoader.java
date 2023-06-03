package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.util.FormattingLoader;

/**
 * @author baka4n
 * {@link Settings}
 */
public class SettingsLoader {
    @SerializedName("rarity_class")
    private String rarity_class_name;
    @SerializedName("rarity")
    private FormattingLoader rarity;
    @SerializedName("recipeRemainder")
    private ItemLoader loader;
    @SerializedName("maxDamage")
    private int max_damage;

    @SerializedName("maxCount")
    private int max_count;

    @SerializedName("foodComponent")
    private FoodComponentLoader foodComponent;

    public static SettingsLoader create(Item item, DefaultedRegistry<Item> registry) {
        SettingsLoader loader = new SettingsLoader();
        loader.rarity_class_name = item.getRarity(new ItemStack(item)).name();
        loader.rarity = FormattingLoader.create(item.getRarity(new ItemStack(item)).formatting);
        loader.max_damage = item.getMaxDamage();
        loader.max_count = item.getMaxCount();
        FoodComponent itemFoodComponent = item.getFoodComponent();
        if (itemFoodComponent != null) FoodComponentLoader.create(itemFoodComponent);
        Item recipeRemainder = item.getRecipeRemainder();
        if (recipeRemainder != null) loader.loader = ItemLoader.create(recipeRemainder, registry);
        return loader;
    }

    public static SettingsLoader create(String rarity_class_name, FormattingLoader rarity, ItemLoader loader, int max_damage, int max_count, FoodComponentLoader foodComponent) {
        SettingsLoader settingsLoader = new SettingsLoader();
        settingsLoader.rarity_class_name = rarity_class_name;
        settingsLoader.rarity = rarity;
        settingsLoader.loader = loader;
        settingsLoader.max_damage = max_damage;
        settingsLoader.max_count = max_count;
        settingsLoader.foodComponent = foodComponent;
        return settingsLoader;
    }
}
