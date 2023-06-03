package union.xenfork.nucleoplasm.json.edit.registry.item.util.a;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

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


    public Identifier getBlock_identifier() {
        String[] split = block_identifier.split(":");
        return Identifier.of(split[0], split[1]);
    }
}
