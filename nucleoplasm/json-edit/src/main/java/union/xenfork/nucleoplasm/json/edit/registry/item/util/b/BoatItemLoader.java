package union.xenfork.nucleoplasm.json.edit.registry.item.util.b;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

/**
 * @author baka4n
 * {@link BoatItem}
 */
public class BoatItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    @SerializedName("chest")
    private boolean chest;
    public BoatItemLoader(BoatItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        type = item.type.name();
        chest = item.chest;
    }

    public static BoatItemLoader create(BoatItem item, DefaultedRegistry<Item> registry) {
        return new BoatItemLoader(item, registry);
    }
}
