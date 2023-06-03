package union.xenfork.nucleoplasm.json.edit.registry.item.util;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.VerticallyAttachableBlockItem;
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
 * {@link AliasedBlockItem}
 */
public class AliasedBlockItemLoader extends BlockItemLoader {
    public AliasedBlockItemLoader(AliasedBlockItem item, DefaultedRegistry<Item> registry) {
        super(item, registry);
    }

    public static AliasedBlockItemLoader create(AliasedBlockItem item, DefaultedRegistry<Item> registry) {
        return new AliasedBlockItemLoader(item, registry);
    }

    public static void initAliasedBlockItem(Logger logger, AliasedBlockItem item, DefaultedRegistry<Item> registry, Path path) {
        AliasedBlockItemLoader load = AliasedBlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  = Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }
}
