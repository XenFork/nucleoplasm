package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
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
