package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import net.minecraft.item.BrushItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link BrushItem}
 */
public class BrushItemLoader extends ItemLoader {
    public BrushItemLoader(BrushItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
