package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader;

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

    public static void initTallBlockItem(Logger logger, TallBlockItem item, DefaultedRegistry<Item> registry, Path path) {
        TallBlockItemLoader load = TallBlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  = Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}
