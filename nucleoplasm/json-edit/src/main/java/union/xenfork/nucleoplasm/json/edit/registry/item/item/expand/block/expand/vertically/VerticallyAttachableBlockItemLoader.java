package union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.vertically;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;

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

    public Identifier getWall_block_identifier() {
        String[] split = wall_block_identifier.split(":");
        return Identifier.of(split[0], split[1]);
    }
}
