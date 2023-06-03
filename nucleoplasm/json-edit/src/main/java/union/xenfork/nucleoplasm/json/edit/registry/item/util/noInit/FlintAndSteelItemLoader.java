package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link FlintAndSteelItem}
 */
public class FlintAndSteelItemLoader extends ItemLoader {
    public FlintAndSteelItemLoader(FlintAndSteelItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
