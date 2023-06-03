package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BedItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader;

/**
 * @author baka4n
 * {@link BedItem}
 */
public class BedItemLoader extends BlockItemLoader {
    public BedItemLoader(BedItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
