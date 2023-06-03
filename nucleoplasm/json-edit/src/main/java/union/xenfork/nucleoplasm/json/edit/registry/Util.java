package union.xenfork.nucleoplasm.json.edit.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.util.AliasedBlockItemLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.json.edit.registry.item.util.AirBlockItemLoader.initAirBlockItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.def.BlockItemLoader.initBlockItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.def.ItemLoader.initItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.AliasedBlockItemLoader.initAliasedBlockItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.MinecartItemLoader.initMinecartItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.OnAStickItemLoader.initOnAStickItemLoader;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.OperatorOnlyBlockItemLoader.initOperatorOnlyBlockItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.PlaceableOnWaterItemLoader.initPlaceableOnWaterItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.SaddleItemLoader.initSaddleItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.ScaffoldingItemLoader.initScaffoldingItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.TallBlockItemLoader.initTallBlockItem;
import static union.xenfork.nucleoplasm.json.edit.registry.item.util.VerticallyAttachableBlockItemLoader.initVerticallyAttachableBlockItem;

public class Util {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void switchUtil(Logger logger, Object t, Path itemPath) {
        Path resolve = preInit(logger, (Item) t, itemPath);
        if (t instanceof OnAStickItem<?> item)
            initOnAStickItemLoader(logger, item, Registries.ITEM, resolve);
        else if (t instanceof MinecartItem item)
            initMinecartItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof SaddleItem item)
            initSaddleItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof AliasedBlockItem item)
            initAliasedBlockItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof ScaffoldingItem item)
            initScaffoldingItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof TallBlockItem item)
            initTallBlockItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof OperatorOnlyBlockItem item)
            initOperatorOnlyBlockItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof PlaceableOnWaterItem item)
            initPlaceableOnWaterItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof VerticallyAttachableBlockItem item)
            initVerticallyAttachableBlockItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof AirBlockItem item)
            initAirBlockItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof BlockItem item)
            initBlockItem(logger, item, Registries.ITEM, resolve);
        else if (t instanceof Item item)
            initItem(logger, item, Registries.ITEM, resolve);

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
