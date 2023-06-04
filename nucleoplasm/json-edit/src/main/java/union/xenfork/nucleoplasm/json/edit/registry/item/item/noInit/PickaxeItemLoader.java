package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tool.expand.mining.MiningToolItemLoader;

/**
 * @author baka4n
 * {@link PickaxeItem}
 */
public class PickaxeItemLoader extends MiningToolItemLoader {
    public PickaxeItemLoader(MiningToolItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
