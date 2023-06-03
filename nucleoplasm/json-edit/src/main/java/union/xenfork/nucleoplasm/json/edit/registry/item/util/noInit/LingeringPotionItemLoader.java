package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link LingeringPotionItem}
 */
public class LingeringPotionItemLoader extends ThrowablePotionItemLoader {
    public LingeringPotionItemLoader(LingeringPotionItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
