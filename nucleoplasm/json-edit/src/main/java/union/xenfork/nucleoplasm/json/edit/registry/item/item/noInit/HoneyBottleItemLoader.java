package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link HoneyBottleItem}
 */
public class HoneyBottleItemLoader extends ItemLoader {
    public HoneyBottleItemLoader(HoneyBottleItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
