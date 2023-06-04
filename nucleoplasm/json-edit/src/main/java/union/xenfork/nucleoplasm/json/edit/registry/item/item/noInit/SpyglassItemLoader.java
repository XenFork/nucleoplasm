package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SpyglassItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link SpyglassItem}
 */
public class SpyglassItemLoader extends ItemLoader {
    public SpyglassItemLoader(SpyglassItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
