package union.xenfork.nucleoplasm.json.edit.registry.item.util.e;

import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link ElytraItem}
 */
public class ElytraItemLoader extends ItemLoader {

    public ElytraItemLoader(ElytraItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static ElytraItemLoader create(ElytraItem item, DefaultedRegistry<Item> registry) {
        return new ElytraItemLoader(item, registry);
    }
}
