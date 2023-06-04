package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link ToolItem}
 */
public class ToolItemLoader extends ItemLoader {
    public ToolItemLoader(ToolItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
