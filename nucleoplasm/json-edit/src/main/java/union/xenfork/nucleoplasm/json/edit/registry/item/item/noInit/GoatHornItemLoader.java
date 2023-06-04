package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link GoatHornItem}
 */
public class GoatHornItemLoader extends ItemLoader {
    public GoatHornItemLoader(GoatHornItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
