package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link BoneMealItem}
 */
public class BoneMealItemLoader extends ItemLoader {
    public BoneMealItemLoader(BoneMealItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
