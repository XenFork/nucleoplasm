package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link BowItem}
 */
public class BowItemLoader extends ItemLoader {
    public BowItemLoader(BowItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
