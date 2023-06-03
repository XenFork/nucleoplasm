package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader;

/**
 * @author baka4n
 * {@link PowderSnowBucketItem}
 */
public class PowderSnowBucketItemLoader extends BlockItemLoader {
    public PowderSnowBucketItemLoader(BlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
