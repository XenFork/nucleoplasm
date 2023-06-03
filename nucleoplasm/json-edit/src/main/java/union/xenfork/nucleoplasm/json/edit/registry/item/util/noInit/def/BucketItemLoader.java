package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit.def;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link BucketItem}
 */
public class BucketItemLoader extends ItemLoader {
    public BucketItemLoader(BucketItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
