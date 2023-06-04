package union.xenfork.nucleoplasm.json.edit.registry.item.item.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link SmithingTemplateItem}
 */
public class SmithingTemplateItemLoader extends ItemLoader {
    public SmithingTemplateItemLoader(SmithingTemplateItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
