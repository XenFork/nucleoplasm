package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.ArmorStandItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link ArmorStandItem}
 */
public class ArmorStandItemLoader extends ItemLoader {
    public ArmorStandItemLoader(ArmorStandItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
