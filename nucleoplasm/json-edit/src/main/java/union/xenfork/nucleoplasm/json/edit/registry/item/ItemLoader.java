package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import union.xenfork.nucleoplasm.json.edit.registry.util.FormattingLoader;

/**
 * @author baka4n
 * {@link Item}
 */
public class ItemLoader {
    @SerializedName("class_name")
    private String class_name;
    @SerializedName("id")
    private String identifier;
    @SerializedName("settings")
    private SettingsLoader settings;

    public ItemLoader(Item item, DefaultedRegistry<Item> registry) {
        class_name = item.getClass().getName();
        identifier = registry.getId(item).toString();
        settings = SettingsLoader.create(item, registry);
    }

    public static ItemLoader create(Item item, DefaultedRegistry<Item> registry) {
        return new ItemLoader(item, registry);
    }

    public String getClass_name() {
        return class_name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public SettingsLoader getSettings() {
        return settings;
    }
}
