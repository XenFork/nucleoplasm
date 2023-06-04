package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.armor.ArmorItemLoader;

/**
 * @author baka4n
 * {@link DyeableArmorItem}
 */
public class DyeableArmorItemLoader extends ArmorItemLoader {
    public DyeableArmorItemLoader(DyeableArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
