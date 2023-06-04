package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.registry.DefaultedRegistry;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link MinecartItem}
 */
public class MinecartItemLoader extends ItemLoader {
    @SerializedName("type")
    private String type;
    public MinecartItemLoader(MinecartItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        type = item.type.name();
    }

    public static MinecartItemLoader create(MinecartItem item, DefaultedRegistry<Item> registry) {
        return new MinecartItemLoader(item, registry);
    }

    public AbstractMinecartEntity.Type getType() {
        return AbstractMinecartEntity.Type.valueOf(type);
    }

}
