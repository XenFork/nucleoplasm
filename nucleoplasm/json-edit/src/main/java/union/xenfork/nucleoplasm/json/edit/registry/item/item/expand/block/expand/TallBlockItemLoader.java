package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand;

import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link TallBlockItem}
 */
public class TallBlockItemLoader extends BlockItemLoader {
    public TallBlockItemLoader(TallBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
    public static TallBlockItemLoader create(TallBlockItem item, DefaultedRegistry<Item> registry) {
        return new TallBlockItemLoader(item, registry);
    }
}
