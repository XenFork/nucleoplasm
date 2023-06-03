package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link CompassItem}
 */
public class CompassItemLoader extends ItemLoader {
    public CompassItemLoader(CompassItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
