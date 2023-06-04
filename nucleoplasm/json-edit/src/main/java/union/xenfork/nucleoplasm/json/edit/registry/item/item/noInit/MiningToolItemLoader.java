package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link MiningToolItem}
 */
public class MiningToolItemLoader extends ToolItemLoader {
    public MiningToolItemLoader(MiningToolItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
