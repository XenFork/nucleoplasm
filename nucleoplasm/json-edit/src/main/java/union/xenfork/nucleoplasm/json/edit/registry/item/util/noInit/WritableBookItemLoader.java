package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.WritableBookItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link WritableBookItem}
 */
public class WritableBookItemLoader extends ItemLoader {
    public WritableBookItemLoader(Item item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
