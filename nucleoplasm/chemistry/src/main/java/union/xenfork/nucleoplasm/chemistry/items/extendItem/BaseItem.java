package union.xenfork.nucleoplasm.chemistry.items.extendItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class BaseItem extends Item {
    public BaseItem(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("mass_num")) {
            return "%s_%d".formatted(super.getTranslationKey(stack), nbt.getInt("mass_num"));
        }
        return super.getTranslationKey(stack);
    }
}
