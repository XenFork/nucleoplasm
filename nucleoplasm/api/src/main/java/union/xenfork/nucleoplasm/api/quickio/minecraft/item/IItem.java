package union.xenfork.nucleoplasm.api.quickio.minecraft.item;

import com.github.artbits.quickio.core.IOEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.api.quickio.utils.ListEntity;

public class IItem extends IOEntity {
    public String namespace, path;

    public IItem(Item item) {
        Identifier id = Registries.ITEM.getId(item);
        namespace = id.getNamespace();
        path = id.getPath();

    }

    public Item get() {
        return Registries.ITEM.get(new Identifier(namespace, path));
    }
}
