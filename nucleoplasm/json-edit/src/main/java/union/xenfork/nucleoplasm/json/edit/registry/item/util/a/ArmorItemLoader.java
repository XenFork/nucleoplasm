package union.xenfork.nucleoplasm.json.edit.registry.item.util.a;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

import java.nio.file.Path;

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
