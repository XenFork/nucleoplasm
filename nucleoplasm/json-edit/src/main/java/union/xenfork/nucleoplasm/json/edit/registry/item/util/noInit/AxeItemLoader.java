package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link AxeItem}
 */
public class AxeItemLoader extends MiningToolItemLoader {
    public AxeItemLoader(AxeItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
