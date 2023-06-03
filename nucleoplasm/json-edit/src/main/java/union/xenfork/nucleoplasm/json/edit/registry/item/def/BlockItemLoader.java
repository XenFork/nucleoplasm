package union.xenfork.nucleoplasm.json.edit.registry.item.def;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link BlockItem}
 */
public class BlockItemLoader extends ItemLoader {

    @SerializedName("block")
    private String block_identifier;

    public BlockItemLoader(BlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
        block_identifier = Registries.BLOCK.getId(item.getBlock()).toString();
    }
    public static BlockItemLoader create(BlockItem item, DefaultedRegistry<Item> registry) {
        return new BlockItemLoader(item, registry);
    }

    public Identifier getBlock_identifier() {
        String[] split = block_identifier.split(":");
        return Identifier.of(split[0], split[1]);
    }
}
