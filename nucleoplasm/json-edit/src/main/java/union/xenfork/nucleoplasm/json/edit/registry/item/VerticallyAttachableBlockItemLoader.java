package union.xenfork.nucleoplasm.json.edit.registry.item;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

/**
 * @author baka4n
 * {@link VerticallyAttachableBlockItem}
 */
public class VerticallyAttachableBlockItemLoader extends BlockItemLoader {
    @SerializedName("wall_block")
    private String wall_block_identifier;

    public VerticallyAttachableBlockItemLoader(VerticallyAttachableBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        this.wall_block_identifier = Registries.BLOCK.getId(item.wallBlock).toString();
    }

    public static VerticallyAttachableBlockItemLoader create(VerticallyAttachableBlockItem item, DefaultedRegistry<Item> registry) {
        return new VerticallyAttachableBlockItemLoader(item, registry);
    }
}
