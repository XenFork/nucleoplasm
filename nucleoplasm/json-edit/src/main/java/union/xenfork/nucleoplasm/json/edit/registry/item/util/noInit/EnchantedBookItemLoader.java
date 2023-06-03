package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link EnchantedBookItem}
 */
public class EnchantedBookItemLoader extends ItemLoader {
    public EnchantedBookItemLoader(EnchantedBookItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
