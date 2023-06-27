package union.xenfork.nucleoplasm.registry.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.registry.event.RegistryEvent;
import union.xenfork.nucleoplasm.registry.registry.ModItems;

import java.util.Arrays;
import java.util.function.Function;

@Debug(export = true)
@Mixin(value = ModItems.class)
public class ModItemMixin {

    public ModItemMixin(String name, int ordunal, Function<Item.Settings, Item> items) {
        throw new AssertionError("can registry item");
    }
    @Shadow(remap = false)
    @Mutable
    @SuppressWarnings({"target", "mapping"})
    public static ModItems[] $VALUES;


    static {
        RegistryEvent.ModItem modItem = new RegistryEvent.ModItem();
        RegistryEvent.REGISTRY_ITEM_EVENT.invoker().registry(modItem);
        modItem.map.forEach(ModItemMixin::add);

    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static void add(String name, Function<Item.Settings, Item> function) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] =  (ModItems) (Object) new ModItemMixin(name, ordinal, function);
    }


}
