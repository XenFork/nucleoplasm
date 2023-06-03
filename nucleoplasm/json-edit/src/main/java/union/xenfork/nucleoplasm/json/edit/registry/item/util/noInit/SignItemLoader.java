package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.v.VerticallyAttachableBlockItemLoader;

/**
 * @author baka4n
 * {@link SignItem}
 */
public class SignItemLoader extends VerticallyAttachableBlockItemLoader {
    public SignItemLoader(SignItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
