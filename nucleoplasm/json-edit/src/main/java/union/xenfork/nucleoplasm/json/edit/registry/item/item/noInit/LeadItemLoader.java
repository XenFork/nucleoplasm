package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.LeadItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link LeadItem}
 */
public class LeadItemLoader extends ItemLoader {

    public LeadItemLoader(LeadItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
