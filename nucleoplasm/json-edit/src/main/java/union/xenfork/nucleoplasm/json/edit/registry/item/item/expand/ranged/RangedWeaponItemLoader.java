package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.ranged;

import net.minecraft.item.Item;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link RangedWeaponItem}
 */
public class RangedWeaponItemLoader extends ItemLoader {
    public RangedWeaponItemLoader(RangedWeaponItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
