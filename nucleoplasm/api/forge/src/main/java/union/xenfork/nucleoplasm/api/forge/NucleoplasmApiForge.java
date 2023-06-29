package union.xenfork.nucleoplasm.api.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;

@Mod(NucleoplasmApi.MOD_ID)
public class NucleoplasmApiForge {
    public NucleoplasmApiForge() {
        NucleoplasmApi.all();
    }
    @EventBusSubscriber(modid = NucleoplasmApi.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class NucleoplasmApiForgeServer {
        public NucleoplasmApiForgeServer() {
            NucleoplasmApi.server();
        }
    }
}
