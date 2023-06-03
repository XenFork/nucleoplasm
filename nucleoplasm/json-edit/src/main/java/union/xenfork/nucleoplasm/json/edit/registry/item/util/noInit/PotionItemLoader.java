package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link PotionItem}
 */
public class PotionItemLoader extends ItemLoader {
    public PotionItemLoader(Item item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
