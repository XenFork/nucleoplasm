package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.NetworkSyncedItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link FilledMapItem}
 */
public class FilledMapItemLoader extends NetworkSyncedItemLoader {

    public FilledMapItemLoader(FilledMapItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
