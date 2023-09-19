package io.github.xenfork.nucleoplasm.core.gen;

import com.mojang.serialization.Codec;
import io.github.xenfork.nucleoplasm.registry.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BrokenStoneFeature extends Feature<BrokenStoneFeatureConfig> {
    public BrokenStoneFeature(Codec<BrokenStoneFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BrokenStoneFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        BrokenStoneFeatureConfig config = context.getConfig();
        Direction offset = Direction.NORTH;
        int height = config.height();
        for (int y = 0; y < height; y++) {
            offset = offset.rotateYClockwise();
            BlockPos blockPos = pos.up(y).offset(offset);

            context.getWorld().setBlockState(blockPos, config.block().get(context.getRandom(), blockPos), 3);
        }
        return true;
    }
}
