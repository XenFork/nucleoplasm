package union.xenfork.nucleoplasm.chemistry.items.extendItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChemicalCompoundItem extends Item {
    public ChemicalCompoundItem(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
//        NbtCompound nbt = stack.getNbt();
//        if (nbt != null && nbt.contains("elements")) {
//            int elements = nbt.getInt("elements");
//            for (int i = 0; i < elements; i++) {
//                String namespace = nbt.getString("item_namespace_" + i);
//                String path = nbt.getString("item_path_" + i);
//                int count = nbt.getInt("item_count_" + i);
//                ItemStack itemStack = new ItemStack(Registries.ITEM.get(Identifier.of(namespace, path)), count);
//                try {
//                    NbtCompound nbtCompound  = StringNbtReader.parse(nbt.getString("item_nbt_" + i));
//                    itemStack.setNbt(nbtCompound);
//                } catch (CommandSyntaxException ignored){}
//                String string = itemStack.getName().getString();
//            }
//        }
        return super.getTranslationKey(stack);
    }
}
