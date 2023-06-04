package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.armor;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link ArmorItem}
 */
public class ArmorItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    @SerializedName("material")
    private ArmorMaterialLoader material;
    public ArmorItemLoader(ArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        type = item.getType().name();
        material = new ArmorMaterialLoader(item);
    }

    public ArmorItem.Type getType() {
        return ArmorItem.Type.valueOf(type);
    }
}
