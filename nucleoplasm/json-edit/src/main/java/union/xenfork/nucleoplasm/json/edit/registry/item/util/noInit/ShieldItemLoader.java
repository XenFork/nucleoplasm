package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link ShieldItem}
 */
public class ShieldItemLoader extends ItemLoader {
    public ShieldItemLoader(ShieldItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
