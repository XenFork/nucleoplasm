package union.xenfork.nucleoplasm.json.edit.registry.item.util.noInit;

import net.minecraft.item.Item;
import net.minecraft.item.KnowledgeBookItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link KnowledgeBookItem}
 */
public class KnowledgeBookItemLoader extends ItemLoader {
    public KnowledgeBookItemLoader(KnowledgeBookItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
}
