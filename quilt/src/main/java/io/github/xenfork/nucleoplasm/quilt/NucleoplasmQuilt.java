package io.github.xenfork.nucleoplasm.quilt;

import io.github.xenfork.nucleoplasm.Nucleoplasm;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ExampleModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        Nucleoplasm.init();
    }
}