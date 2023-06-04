package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.StewItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link StewItem}
 */
public class StewItemLoader extends ItemLoader {
    public StewItemLoader(StewItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
