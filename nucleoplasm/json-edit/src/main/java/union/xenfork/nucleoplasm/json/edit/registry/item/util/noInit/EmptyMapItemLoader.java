package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.EmptyMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.NetworkSyncedItem;
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
