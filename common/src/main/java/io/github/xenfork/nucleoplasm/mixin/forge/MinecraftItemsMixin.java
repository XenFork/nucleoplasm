package io.github.xenfork.nucleoplasm.mixin.forge;

import io.github.xenfork.nucleoplasm.core.item.block.CoalOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.CopperOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.GoldOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.IronOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.vanilla.Leave;
import io.github.xenfork.nucleoplasm.core.item.vanilla.Log;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Items.class, priority = 114514)
public class MinecraftItemsMixin {
    @Mutable
    @Shadow @Final public static Item IRON_ORE;

    @Mutable
    @Shadow @Final public static Item COPPER_ORE;

    @Mutable
    @Shadow @Final public static Item COAL_ORE;

    @Mutable
    @Shadow @Final public static Item DEEPSLATE_COAL_ORE;

    @Mutable
    @Shadow @Final public static Item DEEPSLATE_IRON_ORE;

    @Mutable
    @Shadow @Final public static Item DEEPSLATE_COPPER_ORE;

    @Mutable
    @Shadow @Final public static Item GOLD_ORE;

    @Mutable
    @Shadow @Final public static Item DEEPSLATE_GOLD_ORE;

    @Mutable
    @Shadow @Final public static Item NETHER_GOLD_ORE;

    @Mutable
    @Shadow @Final public static Item OAK_LOG;

    @Mutable
    @Shadow @Final public static Item OAK_PLANKS;

    @Mutable
    @Shadow @Final public static Item OAK_SAPLING;

    @Mutable
    @Shadow @Final public static Item OAK_LEAVES;

    @Mutable
    @Shadow @Final public static Item STRIPPED_OAK_LOG;

    @Mutable
    @Shadow @Final public static Item OAK_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_DARK_OAK_WOOD;

    @Mutable
    @Shadow @Final public static Item DARK_OAK_WOOD;

    @Mutable
    @Shadow @Final public static Item DARK_OAK_LOG;

    @Mutable
    @Shadow @Final public static Item DARK_OAK_PLANKS;

    @Mutable
    @Shadow @Final public static Item DARK_OAK_SAPLING;

    @Mutable
    @Shadow @Final public static Item DARK_OAK_LEAVES;

    @Mutable
    @Shadow @Final public static Item SPRUCE_WOOD;

    @Mutable
    @Shadow @Final public static Item BIRCH_WOOD;

    @Mutable
    @Shadow @Final public static Item JUNGLE_WOOD;

    @Mutable
    @Shadow @Final public static Item ACACIA_WOOD;

    @Mutable
    @Shadow @Final public static Item CHERRY_WOOD;

    @Mutable
    @Shadow @Final public static Item MANGROVE_WOOD;

    @Mutable
    @Shadow @Final public static Item SPRUCE_LOG;

    @Mutable
    @Shadow @Final public static Item BIRCH_LOG;

    @Mutable
    @Shadow @Final public static Item JUNGLE_LOG;

    @Mutable
    @Shadow @Final public static Item ACACIA_LOG;

    @Mutable
    @Shadow @Final public static Item CHERRY_LOG;

    @Mutable
    @Shadow @Final public static Item MANGROVE_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_SPRUCE_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_BIRCH_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_JUNGLE_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_ACACIA_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_CHERRY_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_DARK_OAK_LOG;

    @Mutable
    @Shadow @Final public static Item STRIPPED_MANGROVE_LOG;

    @Mutable
    @Shadow @Final public static Item SPRUCE_PLANKS;

    @Mutable
    @Shadow @Final public static Item BIRCH_PLANKS;

    @Mutable
    @Shadow @Final public static Item JUNGLE_PLANKS;

    @Mutable
    @Shadow @Final public static Item ACACIA_PLANKS;

    @Mutable
    @Shadow @Final public static Item CHERRY_PLANKS;

    @Mutable
    @Shadow @Final public static Item MANGROVE_PLANKS;

    @Mutable
    @Shadow @Final public static Item STRIPPED_OAK_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_SPRUCE_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_BIRCH_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_JUNGLE_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_ACACIA_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_CHERRY_WOOD;

    @Mutable
    @Shadow @Final public static Item STRIPPED_MANGROVE_WOOD;

    @Mutable
    @Shadow @Final public static Item SPRUCE_SAPLING;

    @Mutable
    @Shadow @Final public static Item BIRCH_SAPLING;

    @Mutable
    @Shadow @Final public static Item JUNGLE_SAPLING;

    @Mutable
    @Shadow @Final public static Item ACACIA_SAPLING;

    @Mutable
    @Shadow @Final public static Item CHERRY_SAPLING;

    @Mutable
    @Shadow @Final public static Item SPRUCE_LEAVES;

    @Mutable
    @Shadow @Final public static Item BIRCH_LEAVES;

    @Mutable
    @Shadow @Final public static Item JUNGLE_LEAVES;

    @Mutable
    @Shadow @Final public static Item ACACIA_LEAVES;

    @Mutable
    @Shadow @Final public static Item CHERRY_LEAVES;

    @Mutable
    @Shadow @Final public static Item MANGROVE_LEAVES;

    //对矿物的重定向，内涵化合物等方式
    //铁矿石->我们分成三种nbt由自然界自然生成，
    // 磁铁矿(Fe3O4) Fe2O3·FeO  含有相当数量的Ti 4+以类质同象代替Fe3+ 伴随着Mg 2+ 和 V 3+ 形成矿物亚种
        // 钒磁铁矿 FeV2O4 或 Fe(FeV)O4 含V2O5在有时68.41%~72.04%，一般<5%
        // 钛磁铁矿 Fe(1+x)Fe(2-2x)TixO4 0<x<1 含有TiO2 12%~16%
        // 钒钛铁矿 钒铁钛铁矿固溶体产物
        // 铬磁铁矿
        // 镁磁铁矿
    // 赤铁矿(Fe2O3)
        // 镜铁矿
        // 云母赤铁矿
        // 鲕状或肾状赤铁矿形态呈鲕状或肾状的赤铁矿
    // 菱铁矿(FeCO3)
    // 褐铁矿 是针铁矿、纤铁矿、水针铁矿、水纤铁矿以及含水氧化硅、泥质等的混合物
        // 针铁矿 α-FeO(OH) 包含铁62.9%
        // 水针铁矿 HFeO2.nH20
        // 纤铁矿 γ-FeO(OH)
        // 水纤铁矿 FeO(OH)·nH2O
    // 钛铁矿 FeTiO3
    // 黄铁矿 FeS2
    @SuppressWarnings("DuplicatedCode")
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clinit(CallbackInfo ci) {
        // 煤炭的分类
        COAL_ORE = register(COAL_ORE, new CoalOreBlockItem(((BlockItem)COAL_ORE).getBlock(), new Item.Settings()));
        DEEPSLATE_COAL_ORE = register(DEEPSLATE_COAL_ORE, new CoalOreBlockItem(((BlockItem)DEEPSLATE_COAL_ORE).getBlock(), new Item.Settings()));
        //铁的分类
        IRON_ORE = register(IRON_ORE, new IronOreBlockItem(((BlockItem)IRON_ORE).getBlock(), new Item.Settings()));
        DEEPSLATE_IRON_ORE = register(DEEPSLATE_IRON_ORE, new IronOreBlockItem(((BlockItem)DEEPSLATE_IRON_ORE).getBlock(), new Item.Settings()));
        //铜矿分类
        COPPER_ORE = register(COPPER_ORE, new CopperOreBlockItem(((BlockItem)COPPER_ORE).getBlock(), new Item.Settings()));
        DEEPSLATE_COPPER_ORE = register(DEEPSLATE_COPPER_ORE, new CopperOreBlockItem(((BlockItem)DEEPSLATE_COPPER_ORE).getBlock(), new Item.Settings()));
        //金矿分类
        GOLD_ORE = register(GOLD_ORE, new GoldOreBlockItem(((BlockItem)GOLD_ORE).getBlock(), new Item.Settings()));
        DEEPSLATE_GOLD_ORE = register(DEEPSLATE_GOLD_ORE, new GoldOreBlockItem(((BlockItem)DEEPSLATE_GOLD_ORE).getBlock(), new Item.Settings()));
        NETHER_GOLD_ORE = register(NETHER_GOLD_ORE, new GoldOreBlockItem(((BlockItem)NETHER_GOLD_ORE).getBlock(), new Item.Settings()));

        {
            //木
            OAK_WOOD = register(OAK_WOOD, new Log(((BlockItem) OAK_WOOD).getBlock(), new Item.Settings()));
            SPRUCE_WOOD = register(SPRUCE_WOOD, new Log(((BlockItem) SPRUCE_WOOD).getBlock(), new Item.Settings()));
            BIRCH_WOOD = register(BIRCH_WOOD, new Log(((BlockItem) BIRCH_WOOD).getBlock(), new Item.Settings()));
            JUNGLE_WOOD = register(JUNGLE_WOOD, new Log(((BlockItem) JUNGLE_WOOD).getBlock(), new Item.Settings()));
            ACACIA_WOOD = register(ACACIA_WOOD, new Log(((BlockItem) ACACIA_WOOD).getBlock(), new Item.Settings()));
            CHERRY_WOOD = register(CHERRY_WOOD, new Log(((BlockItem) CHERRY_WOOD).getBlock(), new Item.Settings()));
            MANGROVE_WOOD = register(MANGROVE_WOOD, new Log(((BlockItem) MANGROVE_WOOD).getBlock(), new Item.Settings()));
            DARK_OAK_WOOD = register(DARK_OAK_WOOD, new Log(((BlockItem) DARK_OAK_WOOD).getBlock(), new Item.Settings()));
        }
        {
            //原木
            OAK_LOG = register(OAK_LOG, new Log(((BlockItem) OAK_LOG).getBlock(), new Item.Settings()));
            SPRUCE_LOG = register(SPRUCE_LOG, new Log(((BlockItem) SPRUCE_LOG).getBlock(), new Item.Settings()));
            BIRCH_LOG = register(BIRCH_LOG, new Log(((BlockItem) BIRCH_LOG).getBlock(), new Item.Settings()));
            JUNGLE_LOG = register(JUNGLE_LOG, new Log(((BlockItem) JUNGLE_LOG).getBlock(), new Item.Settings()));
            ACACIA_LOG = register(ACACIA_LOG, new Log(((BlockItem) ACACIA_LOG).getBlock(), new Item.Settings()));
            CHERRY_LOG = register(CHERRY_LOG, new Log(((BlockItem) CHERRY_LOG).getBlock(), new Item.Settings()));
            MANGROVE_LOG = register(MANGROVE_LOG, new Log(((BlockItem) MANGROVE_LOG).getBlock(), new Item.Settings()));
            DARK_OAK_LOG = register(DARK_OAK_LOG, new Log(((BlockItem) DARK_OAK_LOG).getBlock(), new Item.Settings()));//深色橡木原木
        }
        {
            //去皮的木
            STRIPPED_DARK_OAK_WOOD = register(STRIPPED_DARK_OAK_WOOD, new Log(((BlockItem)STRIPPED_DARK_OAK_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_OAK_WOOD = register(STRIPPED_DARK_OAK_WOOD, new Log(((BlockItem)STRIPPED_DARK_OAK_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_SPRUCE_WOOD = register(STRIPPED_SPRUCE_WOOD, new Log(((BlockItem)STRIPPED_SPRUCE_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_BIRCH_WOOD = register(STRIPPED_BIRCH_WOOD, new Log(((BlockItem)STRIPPED_BIRCH_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_JUNGLE_WOOD = register(STRIPPED_JUNGLE_WOOD, new Log(((BlockItem)STRIPPED_JUNGLE_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_ACACIA_WOOD = register(STRIPPED_ACACIA_WOOD, new Log(((BlockItem)STRIPPED_ACACIA_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_CHERRY_WOOD = register(STRIPPED_CHERRY_WOOD, new Log(((BlockItem)STRIPPED_CHERRY_WOOD).getBlock(), new Item.Settings()));
            STRIPPED_MANGROVE_WOOD = register(STRIPPED_MANGROVE_WOOD, new Log(((BlockItem)STRIPPED_MANGROVE_WOOD).getBlock(), new Item.Settings()));

        }
        {
            //去皮原木
            STRIPPED_OAK_LOG = register(STRIPPED_OAK_LOG, new Log(((BlockItem)STRIPPED_OAK_LOG).getBlock(), new Item.Settings()));
            STRIPPED_DARK_OAK_LOG = register(STRIPPED_DARK_OAK_LOG, new Log(((BlockItem)STRIPPED_DARK_OAK_LOG).getBlock(), new Item.Settings()));
            STRIPPED_SPRUCE_LOG = register(STRIPPED_SPRUCE_LOG, new Log(((BlockItem)STRIPPED_SPRUCE_LOG).getBlock(), new Item.Settings()));
            STRIPPED_BIRCH_LOG = register(STRIPPED_BIRCH_LOG, new Log(((BlockItem)STRIPPED_BIRCH_LOG).getBlock(), new Item.Settings()));
            STRIPPED_JUNGLE_LOG = register(STRIPPED_JUNGLE_LOG, new Log(((BlockItem)STRIPPED_JUNGLE_LOG).getBlock(), new Item.Settings()));
            STRIPPED_ACACIA_LOG = register(STRIPPED_ACACIA_LOG, new Log(((BlockItem)STRIPPED_ACACIA_LOG).getBlock(), new Item.Settings()));
            STRIPPED_CHERRY_LOG = register(STRIPPED_CHERRY_LOG, new Log(((BlockItem)STRIPPED_CHERRY_LOG).getBlock(), new Item.Settings()));
            STRIPPED_MANGROVE_LOG = register(STRIPPED_MANGROVE_LOG, new Log(((BlockItem)STRIPPED_MANGROVE_LOG).getBlock(), new Item.Settings()));
        }
        {
            //橡木木板和暗黑橡木木板
            OAK_PLANKS = register(OAK_PLANKS, new Log(((BlockItem) OAK_PLANKS).getBlock(), new Item.Settings()));
            DARK_OAK_PLANKS = register(DARK_OAK_PLANKS, new Log(((BlockItem) DARK_OAK_PLANKS).getBlock(), new Item.Settings()));
            SPRUCE_PLANKS = register(SPRUCE_PLANKS, new Log(((BlockItem) SPRUCE_PLANKS).getBlock(), new Item.Settings()));
            BIRCH_PLANKS = register(BIRCH_PLANKS, new Log(((BlockItem) BIRCH_PLANKS).getBlock(), new Item.Settings()));
            JUNGLE_PLANKS = register(JUNGLE_PLANKS, new Log(((BlockItem) JUNGLE_PLANKS).getBlock(), new Item.Settings()));
            ACACIA_PLANKS = register(ACACIA_PLANKS, new Log(((BlockItem) ACACIA_PLANKS).getBlock(), new Item.Settings()));
            CHERRY_PLANKS = register(CHERRY_PLANKS, new Log(((BlockItem) CHERRY_PLANKS).getBlock(), new Item.Settings()));
            MANGROVE_PLANKS = register(MANGROVE_PLANKS, new Log(((BlockItem) MANGROVE_PLANKS).getBlock(), new Item.Settings()));
        }

        {
            //橡木和暗黑的橡木的树苗
            OAK_SAPLING = register(OAK_SAPLING, new Log(((BlockItem) OAK_SAPLING).getBlock(), new Item.Settings()));
            DARK_OAK_SAPLING = register(DARK_OAK_SAPLING, new Log(((BlockItem) DARK_OAK_SAPLING).getBlock(), new Item.Settings()));
            SPRUCE_SAPLING = register(SPRUCE_SAPLING, new Log(((BlockItem) SPRUCE_SAPLING).getBlock(), new Item.Settings()));
            BIRCH_SAPLING = register(BIRCH_SAPLING, new Log(((BlockItem) BIRCH_SAPLING).getBlock(), new Item.Settings()));
            JUNGLE_SAPLING = register(JUNGLE_SAPLING, new Log(((BlockItem) JUNGLE_SAPLING).getBlock(), new Item.Settings()));
            ACACIA_SAPLING = register(ACACIA_SAPLING, new Log(((BlockItem) ACACIA_SAPLING).getBlock(), new Item.Settings()));
            CHERRY_SAPLING = register(CHERRY_SAPLING, new Log(((BlockItem) CHERRY_SAPLING).getBlock(), new Item.Settings()));
        }

        {
            //橡木树叶和暗黑的橡木树叶
            OAK_LEAVES = register(OAK_LEAVES, new Leave(((BlockItem) OAK_LEAVES).getBlock() ,new Item.Settings()));
            DARK_OAK_LEAVES = register(DARK_OAK_LEAVES, new Leave(((BlockItem) DARK_OAK_LEAVES).getBlock() ,new Item.Settings()));
            SPRUCE_LEAVES = register(SPRUCE_LEAVES, new Leave(((BlockItem) SPRUCE_LEAVES).getBlock() ,new Item.Settings()));
            BIRCH_LEAVES = register(BIRCH_LEAVES, new Leave(((BlockItem) BIRCH_LEAVES).getBlock() ,new Item.Settings()));
            JUNGLE_LEAVES = register(JUNGLE_LEAVES, new Leave(((BlockItem) JUNGLE_LEAVES).getBlock() ,new Item.Settings()));
            ACACIA_LEAVES = register(ACACIA_LEAVES, new Leave(((BlockItem) ACACIA_LEAVES).getBlock() ,new Item.Settings()));
            CHERRY_LEAVES = register(CHERRY_LEAVES, new Leave(((BlockItem) CHERRY_LEAVES).getBlock() ,new Item.Settings()));
            MANGROVE_LEAVES = register(MANGROVE_LEAVES, new Leave(((BlockItem) MANGROVE_LEAVES).getBlock() ,new Item.Settings()));

        }
    }

    private static <T extends Item> T register(T oldT, T newT) {
        return Registry.register(Registries.ITEM, Registries.ITEM.getRawId(oldT), Registries.ITEM.getId(oldT).getPath(), newT);
    }
}
