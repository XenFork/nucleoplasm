package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.DiscFragmentItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link DiscFragmentItem}
 */
public class DiscFragmentItemLoader extends ItemLoader {
    public DiscFragmentItemLoader(DiscFragmentItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
