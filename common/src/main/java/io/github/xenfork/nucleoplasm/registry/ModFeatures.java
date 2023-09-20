package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.Nucleoplasm;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeature;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeatureConfig;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.Nucleoplasm.features;

public enum ModFeatures implements Supplier<Feature<FeatureConfig>> {
    Broken$Stone(new BrokenStoneFeature(BrokenStoneFeatureConfig.CODEC));

    private final RegistrySupplier<? extends Feature<? extends FeatureConfig>> register;

    ModFeatures(Feature<? extends FeatureConfig> feature) {
        register = features.register(id(), () -> feature);
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

    public static void init() {
        features.register();
    }

    public RegistrySupplier<? extends Feature<? extends FeatureConfig>> getRegister() {
        return register;
    }

    @Override
    public Feature<FeatureConfig> get() {
        return (Feature<FeatureConfig>) register.get();
    }
}
