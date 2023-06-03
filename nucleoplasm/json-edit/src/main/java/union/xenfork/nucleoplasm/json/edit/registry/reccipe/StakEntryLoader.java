package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

import java.util.Collection;

public class StakEntryLoader extends EntryLoader {
    @SerializedName("stack")
    private ItemStackLoader stack;

    public StakEntryLoader(Ingredient.StackEntry entry) {
        super(entry.getStacks().toArray(new ItemStack[0]));
        stack = new ItemStackLoader(entry.stack);
    }

    public ItemStack getStack() {
        return stack.getStack();
    }
}
