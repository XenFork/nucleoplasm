package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

/**
 * @author baka4n
 * {@link BowItem}
 */
public class BowItemLoader extends ItemLoader {
    public BowItemLoader(BowItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
