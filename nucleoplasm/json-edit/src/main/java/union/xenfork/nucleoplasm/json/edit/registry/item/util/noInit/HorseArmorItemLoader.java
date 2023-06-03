package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link HorseArmorItem}
 */
public class HorseArmorItemLoader extends ItemLoader {
    public HorseArmorItemLoader(HorseArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
