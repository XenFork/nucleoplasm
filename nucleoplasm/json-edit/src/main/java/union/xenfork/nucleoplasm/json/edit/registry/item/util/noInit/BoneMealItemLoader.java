package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link BoneMealItem}
 */
public class BoneMealItemLoader extends ItemLoader {
    public BoneMealItemLoader(BoneMealItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
