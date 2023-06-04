package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.BannerItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.VerticallyAttachableBlockItemLoader;

/**
 * @author baka4n
 * {@link BannerItem}
 */
public class BannerItemLoader extends VerticallyAttachableBlockItemLoader {
    public BannerItemLoader(BannerItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
