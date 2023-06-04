package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link AliasedBlockItem}
 */
public class AliasedBlockItemLoader extends BlockItemLoader {
    public AliasedBlockItemLoader(AliasedBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static AliasedBlockItemLoader create(AliasedBlockItem item, DefaultedRegistry<Item> registry) {
        return new AliasedBlockItemLoader(item, registry);
    }
}
