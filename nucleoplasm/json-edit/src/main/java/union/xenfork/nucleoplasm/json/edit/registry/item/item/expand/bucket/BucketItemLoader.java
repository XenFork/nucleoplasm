package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.bucket;

import com.google.gson.annotations.SerializedName;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;

/**
 * @author baka4n
 * {@link BucketItem}
 */
public class BucketItemLoader extends ItemLoader {
    @SerializedName("fluid")
    private String fluid;
    public BucketItemLoader(BucketItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        fluid = Registries.FLUID.getId(item.fluid).toString();
    }

    public Fluid getFluid() {
        String[] split = fluid.split(":");
        return Registries.FLUID.get(Identifier.of(split[0], split[1]));
    }
}
