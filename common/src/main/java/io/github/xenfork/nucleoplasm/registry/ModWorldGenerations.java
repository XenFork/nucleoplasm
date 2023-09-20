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

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.Nucleoplasm.configuredFeatures;

public enum ModWorldGenerations {
    Broken$Stone(ModFeatures.Broken$Stone.get(), new BrokenStoneFeatureConfig(1, SimpleBlockStateProvider.of((Block) ModRegistry.Broken$Stone.value2.get())));

    private final Feature<FeatureConfig> feature;
    private final FeatureConfig config;
    private RegistrySupplier<ConfiguredFeature<FeatureConfig, Feature<FeatureConfig>>> register;
    ModWorldGenerations(Feature<FeatureConfig> feature, FeatureConfig config) {
        this.feature = feature;
        this.config = config;
    }

    public static void init() {
        LifecycleEvent.SETUP.register(() -> {
            configuredFeatures = DeferredRegister.create(MOD_ID, RegistryKeys.CONFIGURED_FEATURE);
            for (ModWorldGenerations value : values()) {
                value.register = configuredFeatures.register(value.id(), () -> new ConfiguredFeature<>(value.feature, value.config));
            }
            configuredFeatures.register();
        });
    }

    @NotNull
    private String rename() {
        return name().replace("$", "_").toLowerCase(Locale.ROOT);
    }


    @NotNull
    private Identifier id() {
        return new Identifier(MOD_ID, rename());
    }

    private Identifier id(String sub) {
        return new Identifier(MOD_ID, rename() + "_" + sub);
    }
}
