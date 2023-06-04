package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link EnderEyeItem}
 */
public class EnderEyeItemLoader extends ItemLoader {
    public EnderEyeItemLoader(EnderEyeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
