package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tool.expand.mining.MiningToolItemLoader;

/**
 * @author baka4n
 * {@link ShovelItem}
 */
public class ShovelItemLoader extends MiningToolItemLoader {
    public ShovelItemLoader(ShovelItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
