package union.xenfork.nucleoplasm.chemistry.quickio;

import com.github.artbits.quickio.core.IOEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import union.xenfork.nucleoplasm.api.quickio.minecraft.IItemStack;

import java.util.List;
import java.util.UUID;

public class ItemEntity extends IOEntity {
    public String name;
    public DefaultedList<IItemStack> stacks;
    public ItemEntity(String name) {
        var ref = new Object() {
            String ch = "";
            int count = 0;
            int b = 0;
        };


        name.chars().forEach(value -> {
            switch (ref.b) {
                case 0 -> {
                    
                }
            }
            char c = name.charAt(value);
            if (c >= 'A' && c <= 'Z') {
                ref.b = 0;
                ref.ch += String.valueOf(c);

            } else if (c >= 'a'&& c <= 'z') {
                ref.b = 1;
                ref.ch += String.valueOf(c);
            } else if (c >= '1'&& c <= '9') {
                ref.b = 2;
                ref.count += ref.count * 10 + Integer.parseInt(String.valueOf(c));
            }
        });
    }
}
