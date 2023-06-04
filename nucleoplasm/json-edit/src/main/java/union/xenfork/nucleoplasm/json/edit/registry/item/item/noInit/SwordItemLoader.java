package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link SwordItem}
 */
public class SwordItemLoader extends ToolItemLoader {
    public SwordItemLoader(SwordItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
