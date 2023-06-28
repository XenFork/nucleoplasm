package union.xenfork.nucleoplasm.registry.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;

public enum ModBlocks {
    ;
    public final Block block;
    public final Identifier id;
    ModBlocks(Function<AbstractBlock.Settings, Block> blocks) {
        id = new Identifier("nucleoplasm_registry", name().toLowerCase(Locale.ROOT));
        block = blocks.apply(FabricBlockSettings.create());

    }

    public static void registry() {
        for (ModBlocks value : values()) {
            Registry.register(Registries.BLOCK, value.id, value.block);
        }
    }
}
