package io.github.xenfork.nucleoplasm.mixin.forge;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class MinecraftItemMixin {

    private static final String nucleoplasmName = "nucleoplasm_name";

    @Inject(method = "inventoryTick", at = @At("RETURN"))
    private void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        Identifier id = Registries.ITEM.getId(stack.getItem());

        if (id.getNamespace().equals("minecraft")) {
            switch (id.getPath()) {
                /*ore*/case "gold_ore", "deepslate_gold_ore", "iron_ore", "deepslate_iron_ore", "coal_ore", "deepslate_coal_ore", "nether_gold_ore", "lapis_ore", "deepslate_lapis_ore" , "diamond_ore", "deepslate_diamond_ore" -> /*真正写到的时候再分层*/resetNucleoplasmName(stack, "ores");
                /*log*/case "oak_log", "spruce_log","birch_log", "jungle_log", "acacia_log", "cherry_log", "dark_oak_log", "mangrove_log" -> resetNucleoplasmName(stack, "logs");
                /*Stripped log*/case "stripped_spruce_log", "stripped_birch_log", "stripped_jungle_log", "stripped_acacia_log", "stripped_cherry_log", "stripped_dark_oak_log", "stripped_oak_log", "stripped_mangrove_log" -> resetNucleoplasmName(stack, "stripped logs");
                /*wood*/case "oak_wood", "spruce_wood", "birch_wood", "jungle_wood", "acacia_wood", "cherry_wood", "dark_oak_wood", "mangrove_wood" -> resetNucleoplasmName(stack, "wood");
                /*stripped wood*/case "stripped_oak_wood", "stripped_spruce_wood", "stripped_birch_wood", "stripped_jungle_wood", "stripped_acacia_wood","stripped_cherry_wood", "stripped_dark_oak_wood", "stripped_mangrove_wood" -> resetNucleoplasmName(stack, "stripped wood");
                /*leaves*/case "oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves", "acacia_leaves", "cherry_leaves", "dark_oak_leaves", "mangrove_leaves", "azalea_leaves", "flowering_azalea_leaves" -> resetNucleoplasmName(stack, "leaves");
            }
        }
    }

    private static void resetNucleoplasmName(ItemStack stack, String name) {
        NbtCompound nbt = stack.getNbt() != null ? stack.getNbt() : new NbtCompound();
        if (!nbt.contains(nucleoplasmName)) {//种类分类
            nbt.putString(nucleoplasmName, name);
        }
    }

}
