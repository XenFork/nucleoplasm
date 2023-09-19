package io.github.xenfork.nucleoplasm.registry;

import io.github.xenfork.nucleoplasm.Nucleoplasm;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeature;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeatureConfig;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum ModFeatures {
    broken$stone$gen(new BrokenStoneFeature(BrokenStoneFeatureConfig.CODEC));
    ModFeatures(Feature<? extends FeatureConfig> feature) {
        Nucleoplasm.features.register(id(), () -> feature);
    }

    public static void init() {
        Nucleoplasm.features.register();
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
