package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.HoneycombItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link HoneycombItem}
 */
public class HoneycombItemLoader extends ItemLoader {
    public HoneycombItemLoader(HoneycombItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
