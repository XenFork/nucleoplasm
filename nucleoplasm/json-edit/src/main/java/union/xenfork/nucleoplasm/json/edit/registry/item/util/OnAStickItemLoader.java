package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.OnAStickItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.entity.EntityTypeLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link OnAStickItem}
 */
public class OnAStickItemLoader extends ItemLoader {
    @SerializedName("target")
    private EntityTypeLoader target;
    @SerializedName("damagePerUse")
    private int damagePerUse;
    public OnAStickItemLoader(OnAStickItem<?> item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        target = EntityTypeLoader.create(item.target);
        damagePerUse = item.damagePerUse;
    }
}
