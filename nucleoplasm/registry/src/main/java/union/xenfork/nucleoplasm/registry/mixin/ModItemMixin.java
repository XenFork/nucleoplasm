package union.xenfork.nucleoplasm.registry.mixin;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.registry.registry.ModBlocks;
import union.xenfork.nucleoplasm.registry.registry.ModItems;

import java.util.Arrays;
import java.util.function.Function;

import static union.xenfork.nucleoplasm.registry.impl.ModBlockAndItem.modBlockAndItem;
import static union.xenfork.nucleoplasm.registry.impl.ModItem.modItem;

@Debug(export = true)
@Mixin(value = ModItems.class)
public class ModItemMixin {

    public ModItemMixin(String name, int ordinal, Function<Item.Settings, Item> items) {
        throw new AssertionError("can registry item");
    }
    @Shadow(remap = false)
    @Mutable
    @SuppressWarnings({"target", "mapping"})
    public static ModItems[] $VALUES;


    static {
        modItem.map.forEach(ModItemMixin::add);
        modBlockAndItem.settingsMap.forEach(ModItemMixin::add);
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static void add(String name, Function<Item.Settings, Item> function) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] =  (ModItems) (Object) new ModItemMixin(name, ordinal, function);
    }


    private static void add(String name, Item.Settings settings) {
        add(name, settings1 -> new BlockItem(ModBlocks.valueOf(name).block, settings));
    }
}
