package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link ItemFrameItem}
 */
public class ItemFrameItemLoader extends DecorationItemLoader {
    public ItemFrameItemLoader(ItemFrameItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
