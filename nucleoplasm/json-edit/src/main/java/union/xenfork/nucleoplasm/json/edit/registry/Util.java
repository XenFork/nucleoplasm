package union.xenfork.nucleoplasm.json.edit.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.*;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.AirBlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.AliasedBlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.armor.ArmorItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.BoatItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.ElytraItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.MinecartItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.OnAStickItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.OperatorOnlyBlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.PlaceableOnWaterItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.SaddleItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.ScaffoldingItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.TallBlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.VerticallyAttachableBlockItemLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void switchUtil(Logger logger, Object t, Path itemPath) {
        Path resolve = preInit(logger, (Item) t, itemPath);
        DefaultedRegistry<Item> registry = Registries.ITEM;
        if (t instanceof  ArmorItem item) {
            ArmorItemLoader loader = new ArmorItemLoader(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof BoatItem item) {
            BoatItemLoader loader = new BoatItemLoader(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof ElytraItem item) {
            ElytraItemLoader loader = new ElytraItemLoader(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof OnAStickItem<?> item) {
            OnAStickItemLoader loader = new OnAStickItemLoader(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof MinecartItem item) {
            MinecartItemLoader loader = MinecartItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof SaddleItem item) {
            SaddleItemLoader loader = SaddleItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof AliasedBlockItem item) {
            AliasedBlockItemLoader loader = AliasedBlockItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof ScaffoldingItem item) {
            ScaffoldingItemLoader loader = ScaffoldingItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof TallBlockItem item) {
            TallBlockItemLoader loader = TallBlockItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof OperatorOnlyBlockItem item) {
            OperatorOnlyBlockItemLoader loader = OperatorOnlyBlockItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof PlaceableOnWaterItem item) {
            PlaceableOnWaterItemLoader loader = PlaceableOnWaterItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof VerticallyAttachableBlockItem item) {
            VerticallyAttachableBlockItemLoader loader = VerticallyAttachableBlockItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof AirBlockItem item) {
            AirBlockItemLoader loader = AirBlockItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof BlockItem item) {
            BlockItemLoader loader = BlockItemLoader.create(item, registry);
            loader.init(logger, resolve);
        } else if (t instanceof Item item) {
            ItemLoader loader = ItemLoader.create(item, registry);
            loader.init(logger, resolve);
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
