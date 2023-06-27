package union.xenfork.nucleoplasm.registry.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;

public enum ModGroups {
    ;
    public final ItemGroup group;
    public final Identifier id;
    ModGroups(Function<ItemStack, ItemGroup.Builder> groups) {
        id = new Identifier("nucleoplasm_registry", name().toLowerCase(Locale.ROOT));
        group = groups.apply(ItemStack.EMPTY.copy()).displayName(Text.translatable("itemGroup.%s.%s".formatted(id.getNamespace(), id.getPath()))).build();
    }

    public static void registry() {
        for (ModGroups value : values()) {
            Registry.register(Registries.ITEM_GROUP, value.id, value.group);
        }
    }
}
