package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link TippedArrowItem}
 */
public class TippedArrowItemLoader extends ArrowItemLoader {
    public TippedArrowItemLoader(TippedArrowItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
