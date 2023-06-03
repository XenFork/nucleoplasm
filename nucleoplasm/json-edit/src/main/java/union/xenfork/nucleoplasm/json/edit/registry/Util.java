package union.xenfork.nucleoplasm.json.edit.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.*;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.*;
import union.xenfork.nucleoplasm.json.edit.registry.util.FormattingLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void switchUtil(Logger logger, Object t, Path itemPath) {
        if (t instanceof VerticallyAttachableBlockItem item) {
            Path resolve = preInit(logger, item, itemPath);
            initVerticallyAttachableBlockItem(logger, item, Registries.ITEM, resolve);
        } else if (t instanceof AirBlockItem item) {
            Path resolve = preInit(logger, item, itemPath);
            initAirBlockItem(logger, item, Registries.ITEM, resolve);
        } else if (t instanceof BlockItem item) {
            Path resolve = preInit(logger, item, itemPath);
            initBlockItem(logger, item, Registries.ITEM, resolve);
        } else if (t instanceof Item item) {
            Path resolve = preInit(logger, item, itemPath);
            initItem(logger, item, Registries.ITEM, resolve);
        }
    }

    public static void initVerticallyAttachableBlockItem(Logger logger, VerticallyAttachableBlockItem item, DefaultedRegistry<Item> registry, Path path) {
        VerticallyAttachableBlockItemLoader load = VerticallyAttachableBlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  =Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }

    public static void initAirBlockItem(Logger logger, AirBlockItem item, DefaultedRegistry<Item> registry, Path path) {
        AirBlockItemLoader load = AirBlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  =Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }

    public static void initBlockItem(Logger logger, BlockItem item, DefaultedRegistry<Item> registry, Path path) {
        BlockItemLoader load = BlockItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try(BufferedWriter bw  =Files.newBufferedWriter(path)) {
            bw.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }

    public static void initItem(Logger logger, Item item, DefaultedRegistry<Item> registry, Path path) {
        ItemLoader load = ItemLoader.create(item, registry);
        String json = gson.toJson(load);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            logger.info("fail create to {}", path);
        }
    }

    public static <T extends Item> Path preInit(Logger logger, T item, Path itemPath) {
        Identifier id = Registries.ITEM.getId(item);
        Path resolve = itemPath.resolve(id.getNamespace()).resolve(id.getPath() + ".json");
        Path parent = resolve.getParent();
        if (!Files.exists(parent)) try {
            Files.createDirectories(parent);
        } catch (IOException e) {
            logger.info("fail create {}", resolve);
        }
        return resolve;
    }


}
