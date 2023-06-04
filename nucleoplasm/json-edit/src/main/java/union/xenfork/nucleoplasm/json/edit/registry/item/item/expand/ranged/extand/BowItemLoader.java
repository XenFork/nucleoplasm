package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.ranged.extand;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.ranged.RangedWeaponItemLoader;

/**
 * @author baka4n
 * {@link BowItem}
 */
public class BowItemLoader extends RangedWeaponItemLoader {
    public BowItemLoader(BowItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
