package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import net.minecraft.item.Item;
import net.minecraft.item.SaddleItem;
import net.minecraft.registry.DefaultedRegistry;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.Util.gson;

/**
 * @author baka4n
 * {@link SaddleItem}
 */
public class SaddleItemLoader extends ItemLoader {
    public SaddleItemLoader(SaddleItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static SaddleItemLoader create(SaddleItem item, DefaultedRegistry<Item> registry) {
        return new SaddleItemLoader(item, registry);
    }

    public static void initSaddleItem(Logger logger, SaddleItem item, DefaultedRegistry<Item> registry, Path path) {
        SaddleItemLoader load = SaddleItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}
