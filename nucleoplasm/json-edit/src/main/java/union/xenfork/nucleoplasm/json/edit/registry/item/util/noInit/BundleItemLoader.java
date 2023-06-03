package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link BundleItem}
 */
public class BundleItemLoader extends ItemLoader {
    public BundleItemLoader(BundleItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
