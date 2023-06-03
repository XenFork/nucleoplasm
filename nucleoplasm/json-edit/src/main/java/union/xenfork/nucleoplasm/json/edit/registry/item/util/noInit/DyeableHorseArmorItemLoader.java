package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;

/**
 * @author baka4n
 * {@link DyeableHorseArmorItem}
 */
public class DyeableHorseArmorItemLoader extends HorseArmorItemLoader {
    public DyeableHorseArmorItemLoader(DyeableHorseArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
