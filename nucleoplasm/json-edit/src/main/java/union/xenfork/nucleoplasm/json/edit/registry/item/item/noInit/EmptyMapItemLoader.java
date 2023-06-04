package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.EmptyMapItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link EmptyMapItem}
 */
public class EmptyMapItemLoader extends NetworkSyncedItemLoader {
    public EmptyMapItemLoader(EmptyMapItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
