package io.github.xenfork.nucleoplasm.core.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.function.Function;

public class BrokenStoneBlock extends Block {
    public BrokenStoneBlock(Settings settings) {
        super(settings);
    }
}
