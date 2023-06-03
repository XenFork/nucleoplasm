package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.FireworkStarItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link FireworkStarItem}
 */
public class FireworkStarItemLoader extends ItemLoader {
    public FireworkStarItemLoader(FireworkStarItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
