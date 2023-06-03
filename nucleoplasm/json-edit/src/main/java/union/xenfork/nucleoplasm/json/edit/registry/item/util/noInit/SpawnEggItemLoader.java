package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link SpawnEggItem}
 */
public class SpawnEggItemLoader extends ItemLoader {
    public SpawnEggItemLoader(SpawnEggItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
