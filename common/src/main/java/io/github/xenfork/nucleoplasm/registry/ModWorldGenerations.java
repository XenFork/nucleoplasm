package io.github.xenfork.nucleoplasm.registry;

import com.mojang.serialization.Lifecycle;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.Nucleoplasm;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeature;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import org.jetbrains.annotations.NotNull;

import javax.imageio.spi.RegisterableService;

import java.util.Locale;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.*;

public class ModWorldGenerations {
    public static RegistrySupplier<BrokenStoneFeature> brokenStoneFeatureRegistrySupplier;

    public static void init() {
        Identifier brokenStone = new Identifier(MOD_ID, "broken_stone");
        brokenStoneFeatureRegistrySupplier = features.register(brokenStone, () -> new BrokenStoneFeature(BrokenStoneFeatureConfig.CODEC));
        features.register();
        LifecycleEvent.SETUP.register(() -> {
            ConfiguredFeatures.register();
            configuredFeatures = DeferredRegister.create(MOD_ID, RegistryKeys.CONFIGURED_FEATURE);
            configuredFeatures.register(brokenStone, () -> new ConfiguredFeature<>(brokenStoneFeatureRegistrySupplier.get(), new BrokenStoneFeatureConfig(1, SimpleBlockStateProvider.of((Block) ModRegistry.Broken$Stone.value2.get()))));
            configuredFeatures.register();
        });
    }
}
