package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link EnderPearlItem}
 */
public class EnderPearlItemLoader extends ItemLoader {
    public EnderPearlItemLoader(EnderPearlItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
