package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.NameTagItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link NameTagItem}
 */
public class NameTagItemLoader extends ItemLoader {
    public NameTagItemLoader(NameTagItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
