package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link BedItem}
 */
public class BedItemLoader extends BlockItemLoader {
    public BedItemLoader(BedItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
