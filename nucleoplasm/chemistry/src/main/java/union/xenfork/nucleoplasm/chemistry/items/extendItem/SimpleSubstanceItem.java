package union.xenfork.nucleoplasm.chemistry.items.extendItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import union.xenfork.nucleoplasm.chemistry.items.ModItems;

import java.util.Arrays;
import java.util.function.Function;

public class SimpleSubstanceItem extends Item {
    private final DefaultedList<ItemStack> stacks = DefaultedList.of();
    public SimpleSubstanceItem(Settings settings, ItemStack... stacks) {
        super(settings);
        this.stacks.addAll(Arrays.asList(stacks));
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack defaultStack = super.getDefaultStack();
        NbtCompound nbt = new NbtCompound();
        for (ItemStack stack : stacks) {
            Item item = stack.getItem();
            int count = stack.getCount();
            NbtCompound nbt1 = stack.getNbt();
            for (ModItems value : ModItems.values()) {

            }
        }
        defaultStack.setNbt(nbt);
        return defaultStack;
    }
}
