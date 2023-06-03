package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.a.ArmorItemLoader;

/**
 * @author baka4n
 * {@link DyeableArmorItem}
 */
public class DyeableArmorItemLoader extends ArmorItemLoader {
    public DyeableArmorItemLoader(DyeableArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
