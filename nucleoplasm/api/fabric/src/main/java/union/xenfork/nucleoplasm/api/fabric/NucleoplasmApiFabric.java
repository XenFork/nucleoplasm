package union.xenfork.nucleoplasm.api.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;

public class NucleoplasmApiFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NucleoplasmApi.all();

    }
}
