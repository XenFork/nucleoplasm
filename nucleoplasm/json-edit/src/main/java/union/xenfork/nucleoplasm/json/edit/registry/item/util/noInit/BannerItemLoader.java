package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.BannerItem;
import net.minecraft.item.Item;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.v.VerticallyAttachableBlockItemLoader;

/**
 * @author baka4n
 * {@link BannerItem}
 */
public class BannerItemLoader extends VerticallyAttachableBlockItemLoader {
    public BannerItemLoader(BannerItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
