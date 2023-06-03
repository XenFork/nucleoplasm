package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SnowballItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link SnowballItem}
 */
public class SnowballItemLoader extends ItemLoader {
    public SnowballItemLoader(SnowballItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
