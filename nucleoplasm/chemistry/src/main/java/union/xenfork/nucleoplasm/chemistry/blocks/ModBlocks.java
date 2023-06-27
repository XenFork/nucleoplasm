package union.xenfork.nucleoplasm.chemistry.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;

import static union.xenfork.nucleoplasm.chemistry.Nucleoplasm.modid;

public enum ModBlocks {
    test(settings -> new Block(AbstractBlock.Settings.create()));
    private final Block block;
    public final Identifier id;

    ModBlocks(Function<AbstractBlock.Settings, Block> function) {
        block = function.apply(AbstractBlock.Settings.create());
        id = new Identifier(modid, name().toLowerCase(Locale.ROOT) + "_block");
    }

    public static void registry() {
        for (ModBlocks value : values()) {
            Registry.register(Registries.BLOCK, value.id, value.block);
        }
    }
}
