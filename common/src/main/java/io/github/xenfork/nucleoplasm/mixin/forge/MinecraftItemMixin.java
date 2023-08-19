package io.github.xenfork.nucleoplasm.mixin.forge;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(Item.class)
public class MinecraftItemMixin {

    @Unique
    private static final String nucleoplasm$nucleoplasmName = "nucleoplasm_name";

    @Inject(method = "inventoryTick", at = @At("RETURN"))
    private void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        Identifier id = Registries.ITEM.getId(stack.getItem());

        if (id.getNamespace().equals("minecraft")) {
            switch (id.getPath()) {
                // 栎树(橡树) 植物界 种子植物门 木兰纲 壳斗目 栎属 
                // 房山栎 Quercus × fangshanensis Liou
                // 凤城栎 Quercus × fenchengensis H. W. Jen et L. M. Wang
                // 河北栎 Quercus × hopeiensis Liou
                // 柞槲栎 Quercus × mongolico-dentata Nakai
                // 岩栎 Quercus acrodonta Seemen
                // 麻栎 Quercus acutissima Carruth.
                // 槲栎 Quercus aliena Bl.
                // 川滇高山栎 Quercus aquifolioides Rehd. et Wils.
                // 橿子栎 Quercus baronii Skan
                // 坝王栎 Quercus bawanglingensis Huang, Li et Xing
                // 小叶栎 Quercus chenii Nakai
                // 铁橡栎 Quercus cocciferoides Hand.-Mazz.
                // 槲树 Quercus dentata Thunb.
                // 匙叶栎 Quercus dolicholepis A. Camus
                // 巴东栎 Quercus engleriana Seem.
                // 白栎 Quercus fabri Hance
                // 长苞高山栎 Quercus fimbriata Chun et Huang
                // 锥连栎 Quercus franchetii Skan
                // 川西栎 Quercus gilliana Rehd. et Wils.
                // 大叶栎 Quercus griffithii Hook. f. et Thoms ex Miq.
                // 帽斗栎 Quercus guyavaefolia H. Leveille
                // 澜沧栎 Quercus kingiana Craib
                // 贡山栎 Quercus kongshanensis Y. C. Hsu et H. W. Jen
                // 青树栎 Quercus lanceolata S. Z. Qu et W. H. Zhang
                // 西藏栎 Quercus lodicosa E. F. Warb.
                // 长穗高山栎 Quercus longispica (Hand.-Mazz.) A. Camus
                // 毛叶槲栎 Quercus malacotricha A. Camus
                // 麻栗坡栎 Quercus marlipoensis Hu et Cheng
                // 蒙古栎 Quercus mongolica Fisch. ex Ledeb.
                // 矮高山栎 Quercus monimotricha Hand.-Mazz.
                // 长叶枹栎 Quercus monnula Y. C. Hsu et H. W. Jen
                // 尖叶栎 Quercus oxyphylla (Wils.) Hand.-Mazz.
                // 沼生栎 Quercus palustris Muench.
                // 黄背栎 Quercus pannosa Hand.-Mazz.
                // 乌冈栎 Quercus phillyraeoides A. Gray
                // 光叶高山栎 Quercus pseudosemecarpifolia A. Camus
                // 毛脉高山栎 Quercus rehderiana Hand.-Mazz.
                // 夏栎 Quercus robur L.
                // 高山栎 Quercus semicarpifolia Smith
                // 灰背栎 Quercus senescens Hand.-Mazz.
                // 枹栎 Quercus serrata Thunb.
                // 富宁栎 Quercus setulosa Hick. et A. Camus
                // 刺叶高山栎 Quercus spinosa David ex Franch.
                // 黄山栎 Quercus stewardii Rehd.
                // 太鲁阁栎 Quercus tarokoensis Hayata
                // 通麦栎 Quercus tungmaiensis Y. T. Chang
                // 炭栎 Quercus utilis Hu et Cheng
                // 栓皮栎 Quercus variabilis Bl.
                // 辽东栎 Quercus wutaishanica Mayr
                // 易武栎 Quercus yiwuensis Huagn
                // 云南波罗栎 Quercus yunnanensis Franch。
                /*ore*/case "gold_ore", "deepslate_gold_ore", "iron_ore", "deepslate_iron_ore", "coal_ore", "deepslate_coal_ore", "nether_gold_ore", "lapis_ore", "deepslate_lapis_ore" , "diamond_ore", "deepslate_diamond_ore" -> /*真正写到的时候再分层*/
                        nucleoplasm$resetNucleoplasmName(stack, "ores");
                /*oak log*/case "oak_log" -> {
                    int i = new Random().nextInt(0, 51);
                    switch (i) {
                        case 0: nucleoplasm$resetNucleoplasmName(stack, "Quercus × fangshanensis Liou");
                        case 1: nucleoplasm$resetNucleoplasmName(stack, "Quercus × fenchengensis H. W. Jen et L. M. Wang");
                        case 2: nucleoplasm$resetNucleoplasmName(stack, "Quercus × hopeiensis Liou");
                        case 3: nucleoplasm$resetNucleoplasmName(stack, "Quercus × mongolico-dentata Nakai");
                        case 4: nucleoplasm$resetNucleoplasmName(stack, "Quercus acrodonta Seemen");
                        case 5: nucleoplasm$resetNucleoplasmName(stack, "Quercus acutissima Carruth.");
                        case 6: nucleoplasm$resetNucleoplasmName(stack, "Quercus aliena Bl.");
                        case 7: nucleoplasm$resetNucleoplasmName(stack, "Quercus aquifolioides Rehd. et Wils.");
                        case 8: nucleoplasm$resetNucleoplasmName(stack, "Quercus baronii Skan");
                        case 9: nucleoplasm$resetNucleoplasmName(stack, "Quercus bawanglingensis Huang, Li et Xing");
                        case 10: nucleoplasm$resetNucleoplasmName(stack, "Quercus chenii Nakai");
                        case 11: nucleoplasm$resetNucleoplasmName(stack, "Quercus cocciferoides Hand.-Mazz.");
                        case 12: nucleoplasm$resetNucleoplasmName(stack, "Quercus dentata Thunb.");
                        case 13: nucleoplasm$resetNucleoplasmName(stack, "Quercus dolicholepis A. Camus");
                        case 14: nucleoplasm$resetNucleoplasmName(stack, "Quercus engleriana Seem.");
                        case 15: nucleoplasm$resetNucleoplasmName(stack, "Quercus fabri Hance");
                        case 16: nucleoplasm$resetNucleoplasmName(stack, "Quercus fimbriata Chun et Huang");
                        case 17: nucleoplasm$resetNucleoplasmName(stack, "Quercus franchetii Skan");
                        case 18: nucleoplasm$resetNucleoplasmName(stack, "Quercus gilliana Rehd. et Wils.");
                        case 19: nucleoplasm$resetNucleoplasmName(stack, "Quercus griffithii Hook. f. et Thoms ex Miq.");
                        case 20: nucleoplasm$resetNucleoplasmName(stack, "Quercus guyavaefolia H. Leveille");
                        case 21: nucleoplasm$resetNucleoplasmName(stack, "Quercus kingiana Craib");
                        case 22: nucleoplasm$resetNucleoplasmName(stack, "Quercus kongshanensis Y. C. Hsu et H. W. Jen");
                        case 23: nucleoplasm$resetNucleoplasmName(stack, "Quercus lanceolata S. Z. Qu et W. H. Zhang");
                        case 24: nucleoplasm$resetNucleoplasmName(stack, "Quercus lodicosa E. F. Warb.");
                        case 25: nucleoplasm$resetNucleoplasmName(stack, "Quercus longispica (Hand.-Mazz.) A. Camus");
                        case 26: nucleoplasm$resetNucleoplasmName(stack, "Quercus malacotricha A. Camus");
                        case 27: nucleoplasm$resetNucleoplasmName(stack, "Quercus marlipoensis Hu et Cheng");
                        case 28: nucleoplasm$resetNucleoplasmName(stack, "Quercus mongolica Fisch. ex Ledeb.");
                        case 29: nucleoplasm$resetNucleoplasmName(stack, "Quercus monimotricha Hand.-Mazz.");
                        case 30: nucleoplasm$resetNucleoplasmName(stack, "Quercus monnula Y. C. Hsu et H. W. Jen");
                        case 31: nucleoplasm$resetNucleoplasmName(stack, "Quercus oxyphylla (Wils.) Hand.-Mazz.");
                        case 32: nucleoplasm$resetNucleoplasmName(stack, "Quercus palustris Muench.");
                        case 33: nucleoplasm$resetNucleoplasmName(stack, "Quercus pannosa Hand.-Mazz.");
                        case 34: nucleoplasm$resetNucleoplasmName(stack, "Quercus phillyraeoides A. Gray");
                        case 35: nucleoplasm$resetNucleoplasmName(stack, "Quercus pseudosemecarpifolia A. Camus");
                        case 36: nucleoplasm$resetNucleoplasmName(stack, "Quercus rehderiana Hand.-Mazz.");
                        case 37: nucleoplasm$resetNucleoplasmName(stack, "Quercus robur L.");
                        case 38: nucleoplasm$resetNucleoplasmName(stack, "Quercus semicarpifolia Smith");
                        case 39: nucleoplasm$resetNucleoplasmName(stack, "Quercus senescens Hand.-Mazz.");
                        case 40: nucleoplasm$resetNucleoplasmName(stack, "Quercus serrata Thunb.");
                        case 41: nucleoplasm$resetNucleoplasmName(stack, "Quercus setulosa Hick. et A. Camus");
                        case 42: nucleoplasm$resetNucleoplasmName(stack, "Quercus spinosa David ex Franch.");
                        case 43: nucleoplasm$resetNucleoplasmName(stack, "Quercus stewardii Rehd.");
                        case 44: nucleoplasm$resetNucleoplasmName(stack, "Quercus tarokoensis Hayata");
                        case 45: nucleoplasm$resetNucleoplasmName(stack, "Quercus tungmaiensis Y. T. Chang");
                        case 46: nucleoplasm$resetNucleoplasmName(stack, "Quercus utilis Hu et Cheng");
                        case 47: nucleoplasm$resetNucleoplasmName(stack, "Quercus variabilis Bl.");
                        case 48: nucleoplasm$resetNucleoplasmName(stack, "Quercus wutaishanica Mayr");
                        case 49: nucleoplasm$resetNucleoplasmName(stack, "Quercus yiwuensis Huagn");
                        default: nucleoplasm$resetNucleoplasmName(stack, "Quercus yunnanensis Franch。");
                    }

                }
                /*log*/case "spruce_log","birch_log", "jungle_log", "acacia_log", "cherry_log", "dark_oak_log", "mangrove_log" -> nucleoplasm$resetNucleoplasmName(stack, "logs");
                /*Stripped log*/case "stripped_spruce_log", "stripped_birch_log", "stripped_jungle_log", "stripped_acacia_log", "stripped_cherry_log", "stripped_dark_oak_log", "stripped_oak_log", "stripped_mangrove_log" -> nucleoplasm$resetNucleoplasmName(stack, "stripped logs");
                /*wood*/case "oak_wood", "spruce_wood", "birch_wood", "jungle_wood", "acacia_wood", "cherry_wood", "dark_oak_wood", "mangrove_wood" -> nucleoplasm$resetNucleoplasmName(stack, "wood");
                /*stripped wood*/case "stripped_oak_wood", "stripped_spruce_wood", "stripped_birch_wood", "stripped_jungle_wood", "stripped_acacia_wood","stripped_cherry_wood", "stripped_dark_oak_wood", "stripped_mangrove_wood" -> nucleoplasm$resetNucleoplasmName(stack, "stripped wood");
                /*leaves*/case "oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves", "acacia_leaves", "cherry_leaves", "dark_oak_leaves", "mangrove_leaves", "azalea_leaves", "flowering_azalea_leaves" -> nucleoplasm$resetNucleoplasmName(stack, "leaves");
            }
        }
    }

    @Inject(method = "appendTooltip", at = @At("RETURN"))
    private void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (stack.getNbt() != null) {
            NbtCompound nbt = stack.getNbt();
            if (nbt.contains(nucleoplasm$nucleoplasmName)) {
                String key = "nucleoplasm." + nbt.getString(nucleoplasm$nucleoplasmName);
                String varietyKey = "nucleoplasm.variety";
                MutableText variety = Language.getInstance().hasTranslation(varietyKey) ? Text.translatable(varietyKey) : Text.literal("variety");
                MutableText text = Language.getInstance().hasTranslation(key) ? Text.translatable(key) : Text.literal(key.replace("nucleoplasm.", ""));
                tooltip.add(variety.append(text));
            }
        }
    }

    @Unique
    private static void nucleoplasm$resetNucleoplasmName(ItemStack stack, String name) {
        NbtCompound nbt = stack.getNbt() != null ? stack.getNbt() : new NbtCompound();
        if (!nbt.contains(nucleoplasm$nucleoplasmName)) {//种类分类
            nbt.putString(nucleoplasm$nucleoplasmName, name);
            stack.setNbt(nbt);
        }
    }

}
