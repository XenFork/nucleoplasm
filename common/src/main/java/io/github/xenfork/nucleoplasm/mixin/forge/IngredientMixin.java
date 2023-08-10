package io.github.xenfork.nucleoplasm.mixin.forge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ingredient.class)
public class IngredientMixin {
    @Inject(method = "entryFromJson", at = @At("RETURN"))
    private static void fromJson(JsonObject json, CallbackInfoReturnable<Ingredient.Entry> cir) {

    }
}
