package union.xenfork.nucleoplasm.lib.fabric;

import net.fabricmc.api.DedicatedServerModInitializer;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        union.xenfork.nucleoplasm.lib.NucleoplasmServer.serverInit();
    }
}
