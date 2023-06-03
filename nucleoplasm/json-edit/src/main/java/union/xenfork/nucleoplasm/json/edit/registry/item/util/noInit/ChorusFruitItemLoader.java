package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link ChorusFruitItem}
 */
public class ChorusFruitItemLoader extends ItemLoader {
    public ChorusFruitItemLoader(ChorusFruitItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
