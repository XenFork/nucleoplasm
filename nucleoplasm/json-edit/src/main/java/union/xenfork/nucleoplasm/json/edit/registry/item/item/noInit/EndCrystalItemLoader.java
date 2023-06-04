package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link EndCrystalItem}
 */
public class EndCrystalItemLoader extends ItemLoader {
    public EndCrystalItemLoader(EndCrystalItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
