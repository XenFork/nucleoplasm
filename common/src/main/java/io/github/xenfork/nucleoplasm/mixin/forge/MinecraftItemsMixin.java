package io.github.xenfork.nucleoplasm.mixin.forge;

import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Items.class)
public class MinecraftItemsMixin {
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

}
