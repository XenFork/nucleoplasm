package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.DecorationItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link DecorationItem}
 */
public class DecorationItemLoader extends ItemLoader {
    public DecorationItemLoader(DecorationItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
