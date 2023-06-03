package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.InkSacItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link InkSacItem}
 */
public class InkSacItemLoader extends ItemLoader {
    public InkSacItemLoader(InkSacItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
