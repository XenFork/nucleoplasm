package union.xenfork.nucleoplasm.api.fabric;

import net.fabricmc.api.DedicatedServerModInitializer;

public class NucleoplasmServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        union.xenfork.nucleoplasm.api.NucleoplasmServer.server();
    }
}
