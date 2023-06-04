package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import net.minecraft.item.Item;
import net.minecraft.item.SaddleItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link SaddleItem}
 */
public class SaddleItemLoader extends ItemLoader {
    public SaddleItemLoader(SaddleItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static SaddleItemLoader create(SaddleItem item, DefaultedRegistry<Item> registry) {
        return new SaddleItemLoader(item, registry);
    }
}
