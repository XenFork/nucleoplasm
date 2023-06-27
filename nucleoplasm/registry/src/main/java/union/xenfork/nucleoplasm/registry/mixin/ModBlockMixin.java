package union.xenfork.nucleoplasm.registry.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import union.xenfork.nucleoplasm.registry.event.RegistryEvent;
import union.xenfork.nucleoplasm.registry.registry.ModBlocks;

import java.util.Arrays;
import java.util.function.Function;

import static union.xenfork.nucleoplasm.registry.event.Record.modBlock;
import static union.xenfork.nucleoplasm.registry.event.Record.modBlockAndItem;

@Debug(export = true)
@Mixin(ModBlocks.class)
public class ModBlockMixin {
    public ModBlockMixin(String name, int ordinal, Function<AbstractBlock.Settings, Block> items) {
        throw new AssertionError("can registry item");
    }
    @Shadow(remap = false)
    @Mutable
    @SuppressWarnings({"target", "mapping"})
    public static ModBlocks[] $VALUES;

    static {
        RegistryEvent.REGISTRY_BLOCK_EVENT.invoker().registry(modBlock);
        RegistryEvent.REGISTRY_BLOCK_AND_ITEM_EVENT.invoker().registry(modBlockAndItem);
        modBlock.map.forEach(ModBlockMixin::add);
        modBlockAndItem.map.forEach(ModBlockMixin::add);
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    private static void add(String name, Function<AbstractBlock.Settings, Block> function) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] =  (ModBlocks) (Object) new ModBlockMixin(name, ordinal, function);
    }
}
