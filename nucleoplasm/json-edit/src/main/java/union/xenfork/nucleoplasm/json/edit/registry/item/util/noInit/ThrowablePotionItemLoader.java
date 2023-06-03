package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link ThrowablePotionItem}
 */
public class ThrowablePotionItemLoader extends PotionItemLoader {
    public ThrowablePotionItemLoader(ThrowablePotionItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
