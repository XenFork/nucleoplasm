package union.xenfork.nucleoplasm.lib.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import union.xenfork.nucleoplasm.lib.NucleoplasmServer;

@Mod(union.xenfork.nucleoplasm.lib.Nucleoplasm.MOD_ID)
public class Nucleoplasm {
    public Nucleoplasm() {
        union.xenfork.nucleoplasm.lib.Nucleoplasm.init();
    }
    @Mod.EventBusSubscriber(modid = union.xenfork.nucleoplasm.lib.Nucleoplasm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class Server {
        public Server() {
            NucleoplasmServer.serverInit();
        }

        @SubscribeEvent
        public void playerEvent(TickEvent.PlayerTickEvent event) {
            event.
        }
    }
}
