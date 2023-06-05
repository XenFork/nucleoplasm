package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;

public class StackEntryLoader extends EntryLoader {
    @SerializedName("stack")
    private ItemStackLoader stack;

    public StackEntryLoader(Ingredient.StackEntry entry) {
        super(entry.getStacks().toArray(new ItemStack[0]));
        stack = new ItemStackLoader(entry.stack.getItem(), entry.stack.getCount(), entry.stack.getNbt());
    }

    public StackEntryLoader(ItemConvertible item, int count, NbtCompound nbt) {
        stack = new ItemStackLoader(item, count, nbt);
    }

    public ItemStack getStack() {
        return stack.getStack();
    }
}
