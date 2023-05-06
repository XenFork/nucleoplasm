package union.xenfork.nucleoplasm.chemistry.quickio;

import com.github.artbits.quickio.core.IOEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import union.xenfork.nucleoplasm.api.quickio.minecraft.IItemStack;
import union.xenfork.nucleoplasm.chemistry.Nucleoplasm;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ItemEntity extends IOEntity {
    public String name;
    public DefaultedList<IItemStack> stacks;
    public ItemEntity(String name) {
        stacks = DefaultedList.of();
        var ref = new Object() {
            String ch = "";
            int count = 0;
            boolean b = false;
        };
        name.chars().forEach(value -> {
            char c = name.charAt(value);
            if (c >= 'A' && c <= 'Z') {
                if (!ref.b) {
                    ref.b = true;
                    ref.ch += String.valueOf(c);
                } else {
                    String lowerCase = ref.ch.toLowerCase(Locale.ROOT);
                    Item item = Registries.ITEM.get(Identifier.of(Nucleoplasm.modid, lowerCase));
                    ItemStack stack = new ItemStack(item, ref.count > 0 ? ref.count : 1);
                    stacks.add(new IItemStack(stack));
                }
            } else if (c >= 'a'&& c <= 'z') {
                ref.ch += String.valueOf(c);
            } else if (c >= '0'&& c <= '9') {
                ref.count += ref.count * 10 + Integer.parseInt(String.valueOf(c));
            }
        });
        ItemStack stack = new ItemStack(Registries.ITEM.get(Identifier.of(Nucleoplasm.modid, ref.ch.toLowerCase(Locale.ROOT))))
    }

}
