package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link SpawnEggItem}
 */
public class SpawnEggItemLoader extends ItemLoader {
    public SpawnEggItemLoader(SpawnEggItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
