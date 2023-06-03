package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.EggItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link EggItem}
 */
public class EggItemLoader extends ItemLoader {
    public EggItemLoader(Item item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
