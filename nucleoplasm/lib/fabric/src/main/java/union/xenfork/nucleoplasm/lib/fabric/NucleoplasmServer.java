package union.xenfork.nucleoplasm.lib.fabric;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        union.xenfork.nucleoplasm.lib.NucleoplasmServer.serverInit();
        UseBlockCallback
    }
}
