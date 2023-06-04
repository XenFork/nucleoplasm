package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.NetworkSyncedItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link NetworkSyncedItem}
 */
public class NetworkSyncedItemLoader extends ItemLoader {
    public NetworkSyncedItemLoader(NetworkSyncedItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
