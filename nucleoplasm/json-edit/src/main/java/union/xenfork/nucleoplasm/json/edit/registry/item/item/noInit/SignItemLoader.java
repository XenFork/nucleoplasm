package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.vertically.VerticallyAttachableBlockItemLoader;

/**
 * @author baka4n
 * {@link SignItem}
 */
public class SignItemLoader extends VerticallyAttachableBlockItemLoader {
    public SignItemLoader(SignItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
