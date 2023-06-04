package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link SplashPotionItem}
 */
public class SplashPotionItemLoader extends ThrowablePotionItemLoader {
    public SplashPotionItemLoader(SplashPotionItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
