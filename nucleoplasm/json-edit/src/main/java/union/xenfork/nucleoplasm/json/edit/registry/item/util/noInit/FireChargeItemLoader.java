package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link FireChargeItem}
 */
public class FireChargeItemLoader extends ItemLoader {
    public FireChargeItemLoader(FireChargeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
