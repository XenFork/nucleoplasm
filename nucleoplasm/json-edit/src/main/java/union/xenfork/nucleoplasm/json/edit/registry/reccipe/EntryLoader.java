package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class EntryLoader {
    @SerializedName("stacks")
    private ArrayList<ItemStackLoader> stacks;

    public EntryLoader(ItemStack... stacks) {
        this.stacks = new ArrayList<>();
        for (ItemStack stack : stacks) {
            this.stacks.add(new ItemStackLoader(stack.getItem(), stack.getCount(), stack.getNbt()));
        }
    }


    public Collection<ItemStack> getStacks() {
        return stacks.stream().map(ItemStackLoader::getStack).toList();
    }
}
