package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link EnchantedBookItem}
 */
public class EnchantedBookItemLoader extends ItemLoader {
    public EnchantedBookItemLoader(EnchantedBookItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
