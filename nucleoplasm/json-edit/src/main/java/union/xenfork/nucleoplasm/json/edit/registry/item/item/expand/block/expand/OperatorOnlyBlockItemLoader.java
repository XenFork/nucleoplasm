package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand;

import net.minecraft.item.Item;
import net.minecraft.item.OperatorOnlyBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link OperatorOnlyBlockItem}
 */
public class OperatorOnlyBlockItemLoader extends BlockItemLoader {
    public OperatorOnlyBlockItemLoader(OperatorOnlyBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static OperatorOnlyBlockItemLoader create(OperatorOnlyBlockItem item, DefaultedRegistry<Item> registry) {
        return new OperatorOnlyBlockItemLoader(item, registry);
    }

}
