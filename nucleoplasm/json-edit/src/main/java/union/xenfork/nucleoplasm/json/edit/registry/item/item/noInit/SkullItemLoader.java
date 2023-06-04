package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SkullItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.vertically.VerticallyAttachableBlockItemLoader;

/**
 * @author baka4n
 * {@link SkullItem}
 */
public class SkullItemLoader extends VerticallyAttachableBlockItemLoader {
    public SkullItemLoader(SkullItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
