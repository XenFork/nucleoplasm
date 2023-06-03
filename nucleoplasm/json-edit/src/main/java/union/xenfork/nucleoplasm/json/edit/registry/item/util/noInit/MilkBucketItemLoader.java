package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link MilkBucketItem}
 */
public class MilkBucketItemLoader extends ItemLoader {
    public MilkBucketItemLoader(MilkBucketItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
