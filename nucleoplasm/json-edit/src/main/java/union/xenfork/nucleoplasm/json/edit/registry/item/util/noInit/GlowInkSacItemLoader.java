package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.GlowInkSacItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link GlowInkSacItem}
 */
public class GlowInkSacItemLoader extends ItemLoader {
    public GlowInkSacItemLoader(Item item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
