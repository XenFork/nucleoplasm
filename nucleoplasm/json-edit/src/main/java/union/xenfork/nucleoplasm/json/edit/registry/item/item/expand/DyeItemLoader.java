package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.util.DyeColor;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link DyeItem}
 */
public class DyeItemLoader extends ItemLoader {
    @SerializedName("color")
    private String color;
    public DyeItemLoader(DyeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        color = item.getColor().name();
    }

    public DyeColor getColor() {
        return DyeColor.valueOf(color);
    }
}
