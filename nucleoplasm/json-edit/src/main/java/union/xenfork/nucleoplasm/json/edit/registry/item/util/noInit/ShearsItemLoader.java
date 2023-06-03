package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link ShearsItem}
 */
public class ShearsItemLoader extends ItemLoader {
    public ShearsItemLoader(ShearsItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
