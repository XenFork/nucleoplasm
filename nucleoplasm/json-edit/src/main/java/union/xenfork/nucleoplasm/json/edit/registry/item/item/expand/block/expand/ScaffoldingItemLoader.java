package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand;

import net.minecraft.item.Item;
import net.minecraft.item.ScaffoldingItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link ScaffoldingItem}
 */
public class ScaffoldingItemLoader extends BlockItemLoader {
    public ScaffoldingItemLoader(ScaffoldingItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static ScaffoldingItemLoader create(ScaffoldingItem item, DefaultedRegistry<Item> registry) {
        return new ScaffoldingItemLoader(item, registry);
    }
}
