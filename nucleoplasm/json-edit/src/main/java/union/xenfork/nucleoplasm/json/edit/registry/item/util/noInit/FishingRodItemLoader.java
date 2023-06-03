package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link FishingRodItem}
 */
public class FishingRodItemLoader extends ItemLoader {

    public FishingRodItemLoader(FishingRodItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
