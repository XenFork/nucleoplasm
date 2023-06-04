package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.EnchantedGoldenAppleItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link EnchantedGoldenAppleItem}
 */
public class EnchantedGoldenAppleItemLoader extends ItemLoader {
    public EnchantedGoldenAppleItemLoader(EnchantedGoldenAppleItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
