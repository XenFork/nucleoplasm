package io.github.xenfork.nucleoplasm.mixin.forge;

import com.mojang.serialization.Lifecycle;
import dev.architectury.registry.registries.DeferredRegister;
import io.github.xenfork.nucleoplasm.core.item.block.CoalOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.CopperOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.GoldOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.IronOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.vanilla.Leave;
import io.github.xenfork.nucleoplasm.core.item.vanilla.Log;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Items.class, priority = 114514)
public class MinecraftItemsMixin {
//    @Mutable
//    @Shadow @Final public static Item IRON_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item COPPER_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item COAL_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item DEEPSLATE_COAL_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item DEEPSLATE_IRON_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item DEEPSLATE_COPPER_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item GOLD_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item DEEPSLATE_GOLD_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item NETHER_GOLD_ORE;
//
//    @Mutable
//    @Shadow @Final public static Item OAK_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item OAK_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item OAK_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item OAK_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_OAK_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item OAK_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_DARK_OAK_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item DARK_OAK_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item DARK_OAK_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item DARK_OAK_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item DARK_OAK_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item DARK_OAK_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item SPRUCE_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item BIRCH_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item JUNGLE_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item ACACIA_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item CHERRY_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item MANGROVE_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item SPRUCE_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item BIRCH_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item JUNGLE_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item ACACIA_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item CHERRY_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item MANGROVE_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_SPRUCE_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_BIRCH_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_JUNGLE_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_ACACIA_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_CHERRY_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_DARK_OAK_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_MANGROVE_LOG;
//
//    @Mutable
//    @Shadow @Final public static Item SPRUCE_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item BIRCH_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item JUNGLE_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item ACACIA_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item CHERRY_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item MANGROVE_PLANKS;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_OAK_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_SPRUCE_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_BIRCH_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_JUNGLE_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_ACACIA_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_CHERRY_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item STRIPPED_MANGROVE_WOOD;
//
//    @Mutable
//    @Shadow @Final public static Item SPRUCE_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item BIRCH_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item JUNGLE_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item ACACIA_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item CHERRY_SAPLING;
//
//    @Mutable
//    @Shadow @Final public static Item SPRUCE_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item BIRCH_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item JUNGLE_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item ACACIA_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item CHERRY_LEAVES;
//
//    @Mutable
//    @Shadow @Final public static Item MANGROVE_LEAVES;
//
//    //对矿物的重定向，内涵化合物等方式
//    //铁矿石->我们分成三种nbt由自然界自然生成，
//    // 磁铁矿(Fe3O4) Fe2O3·FeO  含有相当数量的Ti 4+以类质同象代替Fe3+ 伴随着Mg 2+ 和 V 3+ 形成矿物亚种
//        // 钒磁铁矿 FeV2O4 或 Fe(FeV)O4 含V2O5在有时68.41%~72.04%，一般<5%
//        // 钛磁铁矿 Fe(1+x)Fe(2-2x)TixO4 0<x<1 含有TiO2 12%~16%
//        // 钒钛铁矿 钒铁钛铁矿固溶体产物
//        // 铬磁铁矿
//        // 镁磁铁矿
//    // 赤铁矿(Fe2O3)
//        // 镜铁矿
//        // 云母赤铁矿
//        // 鲕状或肾状赤铁矿形态呈鲕状或肾状的赤铁矿
//    // 菱铁矿(FeCO3)
//    // 褐铁矿 是针铁矿、纤铁矿、水针铁矿、水纤铁矿以及含水氧化硅、泥质等的混合物
//        // 针铁矿 α-FeO(OH) 包含铁62.9%
//        // 水针铁矿 HFeO2.nH20
//        // 纤铁矿 γ-FeO(OH)
//        // 水纤铁矿 FeO(OH)·nH2O
//    // 钛铁矿 FeTiO3
//    // 黄铁矿 FeS2
//    @SuppressWarnings("DuplicatedCode")
//    @Inject(method = "<clinit>", at = @At("RETURN"))
//    private static void clinit(CallbackInfo ci) {
//        // 煤炭的分类
//        COAL_ORE = register(Registries.ITEM, COAL_ORE, new CoalOreBlockItem(Blocks.COAL_ORE, new Item.Settings()));
//        DEEPSLATE_COAL_ORE = register(Registries.ITEM, DEEPSLATE_COAL_ORE, new CoalOreBlockItem(Blocks.DEEPSLATE_COAL_ORE, new Item.Settings()));
//        //铁的分类
//        IRON_ORE = register(Registries.ITEM, IRON_ORE, new IronOreBlockItem(Blocks.IRON_ORE, new Item.Settings()));
//        DEEPSLATE_IRON_ORE = register(Registries.ITEM, DEEPSLATE_IRON_ORE, new IronOreBlockItem(Blocks.DEEPSLATE_IRON_ORE, new Item.Settings()));
//        //铜矿分类
//        COPPER_ORE = register(Registries.ITEM, COPPER_ORE, new CopperOreBlockItem(Blocks.COPPER_ORE, new Item.Settings()));
//        DEEPSLATE_COPPER_ORE = register(Registries.ITEM, DEEPSLATE_COPPER_ORE, new CopperOreBlockItem(Blocks.DEEPSLATE_COPPER_ORE, new Item.Settings()));
//        //金矿分类
//        GOLD_ORE = register(Registries.ITEM, GOLD_ORE, new GoldOreBlockItem(Blocks.GOLD_ORE, new Item.Settings()));
//        DEEPSLATE_GOLD_ORE = register(Registries.ITEM, DEEPSLATE_GOLD_ORE, new GoldOreBlockItem(Blocks.DEEPSLATE_GOLD_ORE, new Item.Settings()));
//        NETHER_GOLD_ORE = register(Registries.ITEM, NETHER_GOLD_ORE, new GoldOreBlockItem(Blocks.NETHER_GOLD_ORE, new Item.Settings()));
//        {
//            //木
//            OAK_WOOD = register(Registries.ITEM, OAK_WOOD, new Log(Blocks.OAK_WOOD, new Item.Settings()));
//            SPRUCE_WOOD = register(Registries.ITEM, SPRUCE_WOOD, new Log(Blocks.SPRUCE_WOOD, new Item.Settings()));
//            BIRCH_WOOD = register(Registries.ITEM, BIRCH_WOOD, new Log(Blocks.BIRCH_WOOD, new Item.Settings()));
//            JUNGLE_WOOD = register(Registries.ITEM, JUNGLE_WOOD, new Log(Blocks.JUNGLE_WOOD, new Item.Settings()));
//            ACACIA_WOOD = register(Registries.ITEM, ACACIA_WOOD, new Log(Blocks.ACACIA_WOOD, new Item.Settings()));
//            CHERRY_WOOD = register(Registries.ITEM, CHERRY_WOOD, new Log(Blocks.CHERRY_WOOD, new Item.Settings()));
//            MANGROVE_WOOD = register(Registries.ITEM, MANGROVE_WOOD, new Log(Blocks.MANGROVE_WOOD, new Item.Settings()));
//            DARK_OAK_WOOD = register(Registries.ITEM, DARK_OAK_WOOD, new Log(Blocks.DARK_OAK_WOOD, new Item.Settings()));
//        }
//        {
//            //原木
//            OAK_LOG = register(Registries.ITEM, OAK_LOG, new Log(Blocks.OAK_LOG, new Item.Settings()));
//            SPRUCE_LOG = register(Registries.ITEM, SPRUCE_LOG, new Log(Blocks.SPRUCE_LOG, new Item.Settings()));
//            BIRCH_LOG = register(Registries.ITEM, BIRCH_LOG, new Log(Blocks.BIRCH_LOG, new Item.Settings()));
//            JUNGLE_LOG = register(Registries.ITEM, JUNGLE_LOG, new Log(Blocks.JUNGLE_LOG, new Item.Settings()));
//            ACACIA_LOG = register(Registries.ITEM, ACACIA_LOG, new Log(Blocks.ACACIA_LOG, new Item.Settings()));
//            CHERRY_LOG = register(Registries.ITEM, CHERRY_LOG, new Log(Blocks.CHERRY_LOG, new Item.Settings()));
//            MANGROVE_LOG = register(Registries.ITEM, MANGROVE_LOG, new Log(Blocks.MANGROVE_LOG, new Item.Settings()));
//            DARK_OAK_LOG = register(Registries.ITEM, DARK_OAK_LOG, new Log(Blocks.DARK_OAK_LOG, new Item.Settings()));//深色橡木原木
//        }
//        {
//            //去皮的木
//            STRIPPED_DARK_OAK_WOOD = register(Registries.ITEM, STRIPPED_DARK_OAK_WOOD, new Log(Blocks.STRIPPED_DARK_OAK_WOOD, new Item.Settings()));
//            STRIPPED_OAK_WOOD = register(Registries.ITEM, STRIPPED_DARK_OAK_WOOD, new Log(Blocks.STRIPPED_DARK_OAK_WOOD, new Item.Settings()));
//            STRIPPED_SPRUCE_WOOD = register(Registries.ITEM, STRIPPED_SPRUCE_WOOD, new Log(Blocks.STRIPPED_SPRUCE_WOOD, new Item.Settings()));
//            STRIPPED_BIRCH_WOOD = register(Registries.ITEM, STRIPPED_BIRCH_WOOD, new Log(Blocks.STRIPPED_BIRCH_WOOD, new Item.Settings()));
//            STRIPPED_JUNGLE_WOOD = register(Registries.ITEM, STRIPPED_JUNGLE_WOOD, new Log(Blocks.STRIPPED_JUNGLE_WOOD, new Item.Settings()));
//            STRIPPED_ACACIA_WOOD = register(Registries.ITEM, STRIPPED_ACACIA_WOOD, new Log(Blocks.STRIPPED_ACACIA_WOOD, new Item.Settings()));
//            STRIPPED_CHERRY_WOOD = register(Registries.ITEM, STRIPPED_CHERRY_WOOD, new Log(Blocks.STRIPPED_CHERRY_WOOD, new Item.Settings()));
//            STRIPPED_MANGROVE_WOOD = register(Registries.ITEM, STRIPPED_MANGROVE_WOOD, new Log(Blocks.STRIPPED_MANGROVE_WOOD, new Item.Settings()));
//
//        }
//        {
//            //去皮原木
//            STRIPPED_OAK_LOG = register(Registries.ITEM, STRIPPED_OAK_LOG, new Log(Blocks.STRIPPED_OAK_LOG, new Item.Settings()));
//            STRIPPED_DARK_OAK_LOG = register(Registries.ITEM, STRIPPED_DARK_OAK_LOG, new Log(Blocks.STRIPPED_DARK_OAK_LOG, new Item.Settings()));
//            STRIPPED_SPRUCE_LOG = register(Registries.ITEM, STRIPPED_SPRUCE_LOG, new Log(Blocks.STRIPPED_SPRUCE_LOG, new Item.Settings()));
//            STRIPPED_BIRCH_LOG = register(Registries.ITEM, STRIPPED_BIRCH_LOG, new Log(Blocks.STRIPPED_BIRCH_LOG, new Item.Settings()));
//            STRIPPED_JUNGLE_LOG = register(Registries.ITEM, STRIPPED_JUNGLE_LOG, new Log(Blocks.STRIPPED_JUNGLE_LOG, new Item.Settings()));
//            STRIPPED_ACACIA_LOG = register(Registries.ITEM, STRIPPED_ACACIA_LOG, new Log(Blocks.STRIPPED_ACACIA_LOG, new Item.Settings()));
//            STRIPPED_CHERRY_LOG = register(Registries.ITEM, STRIPPED_CHERRY_LOG, new Log(Blocks.STRIPPED_CHERRY_LOG, new Item.Settings()));
//            STRIPPED_MANGROVE_LOG = register(Registries.ITEM, STRIPPED_MANGROVE_LOG, new Log(Blocks.STRIPPED_MANGROVE_LOG, new Item.Settings()));
//        }
//        {
//            //橡木木板和暗黑橡木木板
//            OAK_PLANKS = register(Registries.ITEM, OAK_PLANKS, new Log(Blocks.OAK_PLANKS, new Item.Settings()));
//            DARK_OAK_PLANKS = register(Registries.ITEM, DARK_OAK_PLANKS, new Log(Blocks.DARK_OAK_PLANKS, new Item.Settings()));
//            SPRUCE_PLANKS = register(Registries.ITEM, SPRUCE_PLANKS, new Log(Blocks.SPRUCE_PLANKS, new Item.Settings()));
//            BIRCH_PLANKS = register(Registries.ITEM, BIRCH_PLANKS, new Log(Blocks.BIRCH_PLANKS, new Item.Settings()));
//            JUNGLE_PLANKS = register(Registries.ITEM, JUNGLE_PLANKS, new Log(Blocks.JUNGLE_PLANKS, new Item.Settings()));
//            ACACIA_PLANKS = register(Registries.ITEM, ACACIA_PLANKS, new Log(Blocks.ACACIA_PLANKS, new Item.Settings()));
//            CHERRY_PLANKS = register(Registries.ITEM, CHERRY_PLANKS, new Log(Blocks.CHERRY_PLANKS, new Item.Settings()));
//            MANGROVE_PLANKS = register(Registries.ITEM, MANGROVE_PLANKS, new Log(Blocks.MANGROVE_PLANKS, new Item.Settings()));
//        }
//
//        {
//            //橡木和暗黑的橡木的树苗
//            OAK_SAPLING = register(Registries.ITEM, OAK_SAPLING, new Log(Blocks.OAK_SAPLING, new Item.Settings()));
//            DARK_OAK_SAPLING = register(Registries.ITEM, DARK_OAK_SAPLING, new Log(Blocks.DARK_OAK_SAPLING, new Item.Settings()));
//            SPRUCE_SAPLING = register(Registries.ITEM, SPRUCE_SAPLING, new Log(Blocks.SPRUCE_SAPLING, new Item.Settings()));
//            BIRCH_SAPLING = register(Registries.ITEM, BIRCH_SAPLING, new Log(Blocks.BIRCH_SAPLING, new Item.Settings()));
//            JUNGLE_SAPLING = register(Registries.ITEM, JUNGLE_SAPLING, new Log(Blocks.JUNGLE_SAPLING, new Item.Settings()));
//            ACACIA_SAPLING = register(Registries.ITEM, ACACIA_SAPLING, new Log(Blocks.ACACIA_SAPLING, new Item.Settings()));
//            CHERRY_SAPLING = register(Registries.ITEM, CHERRY_SAPLING, new Log(Blocks.CHERRY_SAPLING, new Item.Settings()));
//        }
//
//        {
//            //树叶
//            OAK_LEAVES = register(Registries.ITEM, OAK_LEAVES, new Leave(Blocks.OAK_LEAVES ,new Item.Settings()));
//            DARK_OAK_LEAVES = register(Registries.ITEM, DARK_OAK_LEAVES, new Leave(Blocks.DARK_OAK_LEAVES ,new Item.Settings()));
//            SPRUCE_LEAVES = register(Registries.ITEM, SPRUCE_LEAVES, new Leave(Blocks.SPRUCE_LEAVES ,new Item.Settings()));
//            BIRCH_LEAVES = register(Registries.ITEM, BIRCH_LEAVES, new Leave(Blocks.BIRCH_LEAVES ,new Item.Settings()));
//            JUNGLE_LEAVES = register(Registries.ITEM, JUNGLE_LEAVES, new Leave(Blocks.JUNGLE_LEAVES ,new Item.Settings()));
//            ACACIA_LEAVES = register(Registries.ITEM, ACACIA_LEAVES, new Leave(Blocks.ACACIA_LEAVES ,new Item.Settings()));
//            CHERRY_LEAVES = register(Registries.ITEM, CHERRY_LEAVES, new Leave(Blocks.CHERRY_LEAVES ,new Item.Settings()));
//            MANGROVE_LEAVES = register(Registries.ITEM, MANGROVE_LEAVES, new Leave(Blocks.MANGROVE_LEAVES ,new Item.Settings()));
//        }
//    }
//
//    private static <T extends Item> T register(DefaultedRegistry<T> registry,T oldT, T newT) {
//        RegistryEntry<T> set = ((MutableRegistry<T>) registry).set(Registries.ITEM.getRawId(oldT), RegistryKey.of(registry.getKey(), registry.getId(oldT)), newT, Lifecycle.stable());
//        return set.value();
//    }
}
