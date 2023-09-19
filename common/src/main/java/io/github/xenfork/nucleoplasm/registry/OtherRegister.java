package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.injectables.annotations.PlatformOnly;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeature;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeatureConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.*;
@Environment(EnvType.SERVER)
public class OtherRegister {

    public static final DeferredRegister<Feature<?>> features = DeferredRegister.create(MOD_ID, RegistryKeys.FEATURE);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatures = DeferredRegister.create(MOD_ID, RegistryKeys.CONFIGURED_FEATURE);
    private static final RegistrySupplier<BrokenStoneFeature> brokenStoneFeature;

    private static final RegistrySupplier<ConfiguredFeature<BrokenStoneFeatureConfig, BrokenStoneFeature>> brokenConfigured;

    static {
        Identifier brokenStone = new Identifier(MOD_ID, "broken_stone");
        brokenStoneFeature = features.register(brokenStone, () -> new BrokenStoneFeature(BrokenStoneFeatureConfig.CODEC));
        brokenConfigured = configuredFeatures.register(brokenStone, () -> new ConfiguredFeature<>(brokenStoneFeature.get(), new BrokenStoneFeatureConfig(1, SimpleBlockStateProvider.of((Block) ModRegistry.Broken$Stone.value2.get()))));
        features.register();
        configuredFeatures.register();
    }

    public static void init() {}
}
