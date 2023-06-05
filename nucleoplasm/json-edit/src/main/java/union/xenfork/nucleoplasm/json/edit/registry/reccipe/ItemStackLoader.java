package union.xenfork.nucleoplasm.json.edit.registry.reccipe;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
    public ItemStackLoader(ItemStack stack) {
        Item item1 = stack.getItem();
        if (item1 != null) {
            for (Item item2 : Registries.ITEM) {
                if (item1.equals(item2)) {
                    item = Registries.ITEM.getId(item1).toString();
                    count = stack.getCount();
                    if (stack.getNbt() != null) nbt = new NbtLoader(stack.getNbt());
                    break;
                }
            }
        } else {
            item = "";
        }

    }

    public ItemStackLoader(ItemConvertible item, int count, NbtCompound nbt) {
        if (item.asItem() != null) {
            this.item = Registries.ITEM.getId(item.asItem()).toString();
            this.count = count;
            if (nbt != null) {
                this.nbt = new NbtLoader(nbt);
            }
        }
    }

    public ItemStack getStack() {
        if (item.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            String[] split = item.split(":");
            ItemStack itemStack = new ItemStack(Registries.ITEM.get(Identifier.of(split[0], split[1])), count);
            if (nbt != null) {
                itemStack.setNbt(nbt.getNbt());
            }
            return itemStack;
        }
    }
}
