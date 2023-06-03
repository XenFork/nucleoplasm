package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link FireworkRocketItem}
 */
public class FireworkRocketItemLoader extends ItemLoader {
    public FireworkRocketItemLoader(Item item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
