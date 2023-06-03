package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link DyeItem}
 */
public class DyeItemLoader extends ItemLoader {
    public DyeItemLoader(DyeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
