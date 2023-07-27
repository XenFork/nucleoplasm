package io.github.xenfork.nucleoplasm.fabric;

import io.github.xenfork.nucleoplasm.Nucleoplasm;
import net.fabricmc.api.ModInitializer;

public class NucleoplasmFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Nucleoplasm.init();
    }
}