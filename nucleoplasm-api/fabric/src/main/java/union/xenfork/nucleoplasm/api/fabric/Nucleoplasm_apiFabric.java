package union.xenfork.nucleoplasm.api.fabric;

import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import net.fabricmc.api.ModInitializer;

public class Nucleoplasm_apiFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NucleoplasmApi.init();
    }
}