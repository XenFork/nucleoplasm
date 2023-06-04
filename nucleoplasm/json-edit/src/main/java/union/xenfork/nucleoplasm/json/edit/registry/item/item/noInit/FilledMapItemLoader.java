package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
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
