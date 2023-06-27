package union.xenfork.nucleoplasm.chemistry.itemgroups;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;

import static union.xenfork.nucleoplasm.chemistry.Nucleoplasm.modid;

public enum ModItemGroups {
    test(stack -> FabricItemGroup.builder());
    public final ItemGroup itemGroup;
    public final Identifier id;
    ModItemGroups(Function<ItemStack, ItemGroup.Builder> function) {
        id = new Identifier(modid, name().toLowerCase(Locale.ROOT));
        itemGroup = function.apply(ItemStack.EMPTY).displayName(Text.translatable("itemGroup.%s.%s".formatted(modid, id.getPath()))).build();
    }

    public static void registry() {
        for (ModItemGroups value : values()) {
            Registry.register(Registries.ITEM_GROUP, value.id, value.itemGroup);
        }
    }
}
