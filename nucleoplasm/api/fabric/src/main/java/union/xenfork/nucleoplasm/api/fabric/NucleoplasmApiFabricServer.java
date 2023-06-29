package union.xenfork.nucleoplasm.api.fabric;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class NucleoplasmApiFabricServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register(union.xenfork.nucleoplasm.event.lifecycle.ServerLifecycleEvents.SERVER_START_EVENT.invoker()::onServerStarted);
    }
}
