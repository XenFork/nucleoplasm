package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

import java.util.Locale;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.Nucleoplasm.recipes;

public enum ModRecipes {
    ;

    public final Identifier id;
    public final RecipeSerializer<?> serializer;
    public RegistrySupplier<? extends RecipeSerializer<?>> register;

    ModRecipes(RecipeSerializer<?> serializer) {
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
        this.serializer = serializer;
    }

    public static void init() {
		for (ModRecipes recipe : ModRecipes.values()) {
			recipe.register = recipes.register(recipe.id, () -> recipe.serializer);
		}
        recipes.register();
    }
}
