package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link EndCrystalItem}
 */
public class EndCrystalItemLoader extends ItemLoader {
    public EndCrystalItemLoader(EndCrystalItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
