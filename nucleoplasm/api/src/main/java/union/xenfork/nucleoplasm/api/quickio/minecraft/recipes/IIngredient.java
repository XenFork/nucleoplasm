package union.xenfork.nucleoplasm.api.quickio.minecraft.recipes;

import com.github.artbits.quickio.core.IOEntity;
import com.google.gson.JsonElement;
import net.minecraft.recipe.Ingredient;
import union.xenfork.nucleoplasm.api.quickio.minecraft.IJsonElement;

public class IIngredient extends IOEntity {
    private IJsonElement jsonElement;

    public IIngredient(Ingredient ingredient) {
        JsonElement json = ingredient.toJson();
        jsonElement = new IJsonElement(json);
    }

    public Ingredient get() {
        return Ingredient.fromJson(jsonElement.get());
    }
}
