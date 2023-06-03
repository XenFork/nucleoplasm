package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BucketItem;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit.def.BucketItemLoader;

/**
 * @author baka4n
 * {@link EntityBucketItem}
 */
public class EntityBucketItemLoader extends BucketItemLoader {

    public EntityBucketItemLoader(EntityBucketItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
