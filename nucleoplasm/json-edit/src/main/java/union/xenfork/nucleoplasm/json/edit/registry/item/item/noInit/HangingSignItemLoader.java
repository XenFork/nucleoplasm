package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link HangingSignItem}
 */
public class HangingSignItemLoader extends SignItemLoader {
    public HangingSignItemLoader(HangingSignItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
