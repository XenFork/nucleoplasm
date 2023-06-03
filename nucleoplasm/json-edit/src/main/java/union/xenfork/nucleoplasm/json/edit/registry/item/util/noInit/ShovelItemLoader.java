package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link ShovelItem}
 */
public class ShovelItemLoader extends MiningToolItemLoader {
    public ShovelItemLoader(ShovelItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
