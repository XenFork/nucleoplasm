package io.github.xenfork.nucleoplasm.mixin.forge;

import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Ingredient.class)
public class IngredientMixin {

    private static JsonObject entryJsonIn;
    @Inject(method = "entryFromJson", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void entryFromJson(JsonObject json, CallbackInfoReturnable<Ingredient.Entry> cir) {
        entryJsonIn = json;
    }

    @Inject(method = "entryFromJson", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/ItemConvertible;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private static void entryFromJson(JsonObject json, CallbackInfoReturnable<Ingredient.Entry> cir, Item item) {
        ItemStack stack = new ItemStack(item);
        if (entryJsonIn.has("nbt")) {
            JsonObject nbt = entryJsonIn.getAsJsonObject("nbt");
            NbtCompound compound;
            try {
                compound = StringNbtReader.parse(nbt.getAsString());
            } catch (CommandSyntaxException e) {
                compound = null;
            }
            if (compound != null) {
                stack.setNbt(compound);
            }
            cir.setReturnValue(new Ingredient.StackEntry(stack));
            cir.cancel();
        }

    }
}
