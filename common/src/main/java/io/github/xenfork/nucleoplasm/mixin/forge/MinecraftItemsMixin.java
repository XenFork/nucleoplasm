package io.github.xenfork.nucleoplasm.mixin.forge;

import io.github.xenfork.nucleoplasm.core.item.block.CoalOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.CopperOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.GoldOreBlockItem;
import io.github.xenfork.nucleoplasm.core.item.block.IronOreBlockItem;
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

@Mixin(Items.class)
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

    }

    private static <T extends Item> T register(T oldT, T newT) {
        return Registry.register(Registries.ITEM, Registries.ITEM.getRawId(oldT), Registries.ITEM.getId(oldT).getPath(), newT);
    }
}
