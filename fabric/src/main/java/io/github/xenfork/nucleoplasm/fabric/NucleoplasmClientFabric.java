package io.github.xenfork.nucleoplasm.fabric;

import dev.felnull.specialmodelloader.api.event.SpecialModelLoaderEvents;
import io.github.xenfork.nucleoplasm.Nucleoplasm;
import net.fabricmc.api.ClientModInitializer;

public class NucleoplasmClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SpecialModelLoaderEvents.LOAD_SCOPE.register(location -> Nucleoplasm.MOD_ID.equals(location.getNamespace()));
    }
}
