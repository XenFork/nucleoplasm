package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link MusicDiscItem}
 */
public class MusicDiscItemLoader extends ItemLoader {
    public MusicDiscItemLoader(MusicDiscItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
