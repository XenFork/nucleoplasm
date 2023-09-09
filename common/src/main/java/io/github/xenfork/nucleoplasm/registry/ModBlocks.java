package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.block.BrokenStoneBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum ModBlocks implements Supplier<Block> {

    ;


    public final Block block;
    public final Identifier id;

    public RegistrySupplier<Block> register;

    ModBlocks(Function<AbstractBlock.Settings, Block> block) {
        this.block = block.apply(AbstractBlock.Settings.create());
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
    }

    @Override
    public Block get() {
        return register.get();
    }
}
