package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SkullItem;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.v.VerticallyAttachableBlockItemLoader;

/**
 * @author baka4n
 * {@link SkullItem}
 */
public class SkullItemLoader extends VerticallyAttachableBlockItemLoader {
    public SkullItemLoader(SkullItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
