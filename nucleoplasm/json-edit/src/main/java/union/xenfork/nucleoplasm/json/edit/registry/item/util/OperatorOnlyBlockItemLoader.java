package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import net.minecraft.item.Item;
import net.minecraft.item.OperatorOnlyBlockItem;
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
 * {@link OperatorOnlyBlockItem}
 */
public class OperatorOnlyBlockItemLoader extends BlockItemLoader {
    public OperatorOnlyBlockItemLoader(OperatorOnlyBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static OperatorOnlyBlockItemLoader create(OperatorOnlyBlockItem item, DefaultedRegistry<Item> registry) {
        return new OperatorOnlyBlockItemLoader(item, registry);
    }

    public static void initOperatorOnlyBlockItem(Logger logger, OperatorOnlyBlockItem item, DefaultedRegistry<Item> registry, Path path) {
        OperatorOnlyBlockItemLoader load = OperatorOnlyBlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  = Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}
