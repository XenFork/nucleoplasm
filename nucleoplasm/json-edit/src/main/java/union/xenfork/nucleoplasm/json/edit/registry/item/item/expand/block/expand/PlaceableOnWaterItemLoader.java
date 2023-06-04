package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand;

import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

/**
 * @author baka4n
 * {@link PlaceableOnWaterItem}
 */
public class PlaceableOnWaterItemLoader extends BlockItemLoader {
    public PlaceableOnWaterItemLoader(PlaceableOnWaterItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static PlaceableOnWaterItemLoader create(PlaceableOnWaterItem item, DefaultedRegistry<Item> registry) {
        return new PlaceableOnWaterItemLoader(item, registry);
    }
}
