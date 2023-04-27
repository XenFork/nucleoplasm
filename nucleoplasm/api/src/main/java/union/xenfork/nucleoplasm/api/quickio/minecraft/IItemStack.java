package union.xenfork.nucleoplasm.api.quickio.minecraft;

import com.github.artbits.quickio.core.IOEntity;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;

public class IItemStack extends IOEntity {
    public IItem item;
    public int count;
    public INbt nbt;

    public IItemStack(ItemStack stack) {
        item = new IItem(stack.getItem());
        count = stack.getCount();
        nbt = new INbt(stack.getNbt());
    }

    public ItemStack get() {
        ItemStack itemStack = new ItemStack(item.get(), count);
        itemStack.setNbt(nbt.get());
        return itemStack;
    }

    public static class INbt extends IOEntity {
        public String nbt;
        public INbt(NbtCompound nbt) {
            if (nbt != null) {
                this.nbt = nbt.asString();
            }

        }

        public NbtCompound get() {
            try {
                return StringNbtReader.parse(nbt);
            } catch (CommandSyntaxException e) {
                NucleoplasmApi.logger.error(e.getMessage());
            }
            return new NbtCompound();
        }
    }

    public static class IItem extends IOEntity {
        public String namespace, path;
        public IItem(Item item) {
            Identifier id = Registries.ITEM.getId(item);
            namespace = id.getNamespace();
            path = id.getPath();
        }

        public Item get() {
            return Registries.ITEM.get(new Identifier(namespace, path));
        }
    }
}
