package union.xenfork.nucleoplasm.registry.mixin;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.registry.registry.ModGroups;

import java.util.Arrays;
import java.util.function.Function;

import static union.xenfork.nucleoplasm.registry.impl.ModGroup.modGroup;

@Debug(export = true)
@Mixin(ModGroups.class)
public class ModGroupMixin {
    public ModGroupMixin(String name, int ordinal, Function<ItemStack, ItemGroup.Builder> function) {
        throw new AssertionError("can registry item");
    }
    @Shadow(remap = false)
    @Mutable
    @SuppressWarnings({"target", "mapping"})
    public static ModGroups[] $VALUES;

    static {
        modGroup.map.forEach(ModGroupMixin::add);
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static void add(String name, Function<ItemStack, ItemGroup.Builder> function) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] =  (ModGroups) (Object) new ModGroupMixin(name, ordinal, function);
    }


}
