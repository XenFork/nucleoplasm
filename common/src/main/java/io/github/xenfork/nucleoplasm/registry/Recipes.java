package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Locale;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum Recipes {
    ;
    public final Identifier id;
    final RecipeSerializer<?> serializer;
    public RegistrySupplier<? extends RecipeSerializer<?>> register;

    public static final DeferredRegister<RecipeSerializer<?>> recipes = DeferredRegister.create(MOD_ID, RegistryKeys.RECIPE_SERIALIZER);

    Recipes(RecipeSerializer<?> serializer) {
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
        this.serializer = serializer;
    }

    public static void init() {
        for (Recipes recipe : values()) {
            recipe.register = recipes.register(recipe.id.getPath(), () -> recipe.serializer);
        }
        recipes.register();
    }
}
