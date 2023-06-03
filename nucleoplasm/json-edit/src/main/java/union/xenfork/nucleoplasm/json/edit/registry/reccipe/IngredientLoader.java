package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * @author baka4n
 * {@link Ingredient}
 */
public class IngredientLoader {
    @SerializedName("types")
    private ArrayList<Pair<String, EntryLoader>> types;
    public IngredientLoader(Ingredient ingredient) {
        Ingredient.Entry[] entries = ingredient.entries;
        types = new ArrayList<>();
        for (Ingredient.Entry entry : entries) {
            if (entry instanceof Ingredient.TagEntry tagEntry) {
                types.add(new Pair<>(tagEntry.getClass().getName(), new TagEntryLoader(tagEntry)));
            } else if (entry instanceof Ingredient.StackEntry stackEntry) {
                types.add(new Pair<>(stackEntry.getClass().getName(), new StakEntryLoader(stackEntry)));
            }
        }
    }
}
