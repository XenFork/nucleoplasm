package union.xenfork.nucleoplasm.api.quickio.minecraft.item;

import com.github.artbits.quickio.core.IOEntity;
import net.minecraft.item.ItemStack;
import union.xenfork.nucleoplasm.api.quickio.minecraft.nbt.INbt;
import union.xenfork.nucleoplasm.api.quickio.utils.ListEntity;

public class IItemStack extends ListEntity {
    public IItem item;
    public int count, size;
    public INbt nbt;

    public IItemStack(ItemStack stack) {
        super(0);
        item = new IItem(stack.getItem());
        count = stack.getCount();
        nbt = new INbt(stack.getNbt());
    }

    public IItemStack(ItemStack stack , int size) {
        super(size);
        item = new IItem(stack.getItem());
        count = stack.getCount();
        nbt = new INbt(stack.getNbt());
    }

    public ItemStack get() {
        ItemStack itemStack = new ItemStack(item.get(), count);
        itemStack.setNbt(nbt.get());
        return itemStack;
    }
}
