package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * @author baka4n
 * {@link ItemStack}
 */
public class ItemStackLoader {
    @SerializedName("item")
    private String item;
    @SerializedName("count")
    private int count;
    @SerializedName("nbt")
    private NbtLoader nbt;
    public ItemStackLoader(@NotNull ItemStack stack) {
        item = Registries.ITEM.getId(stack.getItem()).toString();
        count = stack.getCount();
        if (stack.getNbt() != null) nbt = new NbtLoader(stack.getNbt());
    }

    public ItemStack getStack() {
        String[] split = item.split(":");
        ItemStack itemStack = new ItemStack(Registries.ITEM.get(Identifier.of(split[0], split[1])), count);
        if (nbt != null) {
            itemStack.setNbt(nbt.getNbt());
        }
        return itemStack;
    }
}
