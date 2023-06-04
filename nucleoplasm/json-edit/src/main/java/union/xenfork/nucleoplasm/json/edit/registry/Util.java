package union.xenfork.nucleoplasm.json.edit.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.item.*;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.ItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.*;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.armor.ArmorItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.BlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.*;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.vertically.VerticallyAttachableBlockItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.vertically.extand.BannerItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.bucket.BucketItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.bucket.expand.EntityBucketItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.horsearmor.HorseArmorItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tools.ToolItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.tools.expand.mining.MiningToolItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.block.expand.PowderSnowBucketItemLoader;
import union.xenfork.nucleoplasm.json.edit.registry.item.item.expand.SmithingTemplateItemLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static <T extends Item> void switchUtil(Logger logger, T t, Path itemPath, DefaultedRegistry<Item> registry) {

        ItemLoader loader;
        Path resolve = preInit(logger,(Item) t, itemPath);
        if (t instanceof BlockItem) {
            loader = switchUtilBlockItem(t, registry);
        } else if (t instanceof ArmorItem) {
            loader = new ArmorItemLoader((ArmorItem) t, registry);
        } else if (t instanceof BucketItem) {
            if (t instanceof EntityBucketItem) {
                loader = new EntityBucketItemLoader((EntityBucketItem) t, registry);
            } else {
                loader = new BucketItemLoader((BucketItem) t, registry);
            }
        } else if (t instanceof ToolItem) {
            if (t instanceof MiningToolItem) {
                loader = new MiningToolItemLoader((MiningToolItem) t, registry);
            } else {
                loader = new ToolItemLoader((ToolItem) t, registry);
            }
        } else {
            loader = switchUtilItem(t, registry);
        }
        loader.init(logger, resolve);
    }

    public static <T extends Item> ItemLoader switchUtilBlockItem(T t, DefaultedRegistry<Item> registry) {
        if (t instanceof PowderSnowBucketItem) {
            return new PowderSnowBucketItemLoader((PowderSnowBucketItem) t, registry);
        } else if (t instanceof VerticallyAttachableBlockItem) {
            if (t instanceof BannerItem) {
                return new BannerItemLoader((BannerItem) t, registry);
            } else {
                return new VerticallyAttachableBlockItemLoader((VerticallyAttachableBlockItem) t, registry);
            }
        } else if (t instanceof TallBlockItem) {
            return new TallBlockItemLoader((TallBlockItem) t, registry);
        } else if (t instanceof ScaffoldingItem) {
            return new ScaffoldingItemLoader((ScaffoldingItem) t, registry);
        } else if (t instanceof PlaceableOnWaterItem) {
            return new PlaceableOnWaterItemLoader((PlaceableOnWaterItem) t, registry);
        } else if (t instanceof OperatorOnlyBlockItem) {
            return new OperatorOnlyBlockItemLoader((OperatorOnlyBlockItem) t, registry);
        } else if (t instanceof AliasedBlockItem) {
            return new AliasedBlockItemLoader((AliasedBlockItem) t, registry);
        } else {
            return new BlockItemLoader((BlockItem) t, registry);
        }
    }

    /**
     *
     * @param t {@link Item}
     * @param registry {@link Registries}
     * @return {@link ItemLoader}
     * @param <T> {@link Item}
     */
    public static <T extends Item> ItemLoader switchUtilItem(T t, DefaultedRegistry<Item> registry) {

        if (t instanceof SmithingTemplateItem) {
            return new SmithingTemplateItemLoader((SmithingTemplateItem) t, registry);
        } else if (t instanceof MusicDiscItem) {
            return new MusicDiscItemLoader((MusicDiscItem) t, registry);
        } else if (t instanceof DecorationItem) {
            return new DecorationItemLoader((DecorationItem) t, registry);
        } else if (t instanceof BundleItem) {
            return new BundleItemLoader((BundleItem) t, registry);
        } else if (t instanceof BrushItem) {
            return new BrushItemLoader((BrushItem) t, registry);
        } else if (t instanceof GoatHornItem) {
            return new GoatHornItemLoader((GoatHornItem) t, registry);
        } else if (t instanceof DyeItem) {
            return new DyeItemLoader((DyeItem) t, registry);
        } else if (t instanceof HorseArmorItem) {
            return new HorseArmorItemLoader((HorseArmorItem) t, registry);
        } else if (t instanceof BannerPatternItem) {
            return new BannerPatternItemLoader((BannerPatternItem) t, registry);
        } else if (t instanceof SaddleItem) {
            return new SaddleItemLoader((SaddleItem) t, registry);
        } else if (t instanceof OnAStickItem<?>) {
            return new OnAStickItemLoader((OnAStickItem<?>) t, registry);
        } else if (t instanceof MinecartItem) {
            return new MinecartItemLoader((MinecartItem) t, registry);
        } else if (t instanceof AirBlockItem) {
            return new AirBlockItemLoader((AirBlockItem) t, registry);
        } else {
            return new ItemLoader(t, registry);
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
