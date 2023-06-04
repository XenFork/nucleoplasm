package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link TridentItem}
 */
public class TridentItemLoader extends ItemLoader {
    public TridentItemLoader(TridentItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
