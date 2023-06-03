package union.xenfork.nucleoplasm.json.edit.registry.item.util.t;

import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.InitImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link TallBlockItem}
 */
public class TallBlockItemLoader extends BlockItemLoader {
    public TallBlockItemLoader(TallBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }
    public static TallBlockItemLoader create(TallBlockItem item, DefaultedRegistry<Item> registry) {
        return new TallBlockItemLoader(item, registry);
    }
}
