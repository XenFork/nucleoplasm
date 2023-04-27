package union.xenfork.nucleoplasm.chemistry.quickio;

import com.github.artbits.quickio.core.IOEntity;
import net.minecraft.item.ItemStack;
import union.xenfork.nucleoplasm.api.quickio.minecraft.IItemStack;

import java.util.List;
import java.util.UUID;

public class ItemEntity extends IOEntity {
    public String name;
    public List<IItemStack> stacks;
    public ItemEntity(String name) {
        String ch = "";
    }
}
