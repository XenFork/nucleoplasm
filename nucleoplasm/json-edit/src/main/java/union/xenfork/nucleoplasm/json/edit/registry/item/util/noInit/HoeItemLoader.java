package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link HoeItem}
 */
public class HoeItemLoader extends MiningToolItemLoader {

    public HoeItemLoader(HoeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
