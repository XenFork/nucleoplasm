package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;

/**
 * @author baka4n
 * {@link AirBlockItem}
 */
public class AirBlockItemLoader extends ItemLoader {
    @SerializedName("block")
    private String block_identifier;

    public AirBlockItemLoader(AirBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        block_identifier = Registries.BLOCK.getId(item.block).toString();
    }

    public static AirBlockItemLoader create(AirBlockItem item, DefaultedRegistry<Item> registry) {
        return new AirBlockItemLoader(item, registry);
    }
}
