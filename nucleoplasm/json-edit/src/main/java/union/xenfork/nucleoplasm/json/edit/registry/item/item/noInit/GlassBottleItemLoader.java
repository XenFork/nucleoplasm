package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link GlassBottleItem}
 */
public class GlassBottleItemLoader extends ItemLoader {
    public GlassBottleItemLoader(GlassBottleItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
