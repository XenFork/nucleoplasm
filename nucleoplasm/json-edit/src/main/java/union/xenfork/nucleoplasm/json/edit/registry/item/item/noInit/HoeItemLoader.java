package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tool.expand.mining.MiningToolItemLoader;

/**
 * @author baka4n
 * {@link HoeItem}
 */
public class HoeItemLoader extends MiningToolItemLoader {

    public HoeItemLoader(HoeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
