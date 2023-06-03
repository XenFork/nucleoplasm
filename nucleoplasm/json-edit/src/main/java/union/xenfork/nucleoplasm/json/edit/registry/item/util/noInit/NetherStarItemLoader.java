package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.NetherStarItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link NetherStarItem}
 */
public class NetherStarItemLoader extends ItemLoader {
    public NetherStarItemLoader(NetherStarItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
