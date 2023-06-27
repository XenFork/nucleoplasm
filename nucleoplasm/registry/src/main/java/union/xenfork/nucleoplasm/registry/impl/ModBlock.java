package union.xenfork.nucleoplasm.registry.impl;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.function.Function;

public class ModBlock extends ModDef<String, Function<AbstractBlock.Settings, Block>> {
    public static final ModBlock modBlock = new ModBlock();
}
