package io.github.xenfork.nucleoplasm.core.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record BrokenStoneFeatureConfig(IntProvider height, BlockStateProvider block) implements FeatureConfig {
    public static final Codec<BrokenStoneFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("height").forGetter(BrokenStoneFeatureConfig::height),
            BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(BrokenStoneFeatureConfig::block)
    ).apply(instance, instance.stable(BrokenStoneFeatureConfig::new)));
}
