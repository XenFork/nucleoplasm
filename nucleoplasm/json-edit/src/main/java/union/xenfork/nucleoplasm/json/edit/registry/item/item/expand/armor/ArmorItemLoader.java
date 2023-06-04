package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.armor;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

import java.util.Locale;

/**
 * @author baka4n
 * {@link ArmorItem}
 */
public class ArmorItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    @SerializedName("material")
    private String material;
    public ArmorItemLoader(ArmorItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        type = item.getType().name();
        material = item.getMaterial().getName().toUpperCase(Locale.ROOT);
    }

    public ArmorItem.Type getType() {
        return ArmorItem.Type.valueOf(type);
    }
    public ArmorMaterial getMaterial() {
        return ArmorMaterials.valueOf(material);
    }
}
