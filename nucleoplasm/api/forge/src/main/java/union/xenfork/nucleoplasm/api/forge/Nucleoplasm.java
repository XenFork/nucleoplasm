package union.xenfork.nucleoplasm.api.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(union.xenfork.nucleoplasm.api.Nucleoplasm.MOD_ID)
public class Nucleoplasm {
    public Nucleoplasm() {
        union.xenfork.nucleoplasm.api.Nucleoplasm.all();
    }
    @EventBusSubscriber(modid = union.xenfork.nucleoplasm.api.Nucleoplasm.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class NucleoplasmApiForgeServer {
        public NucleoplasmApiForgeServer() {
            union.xenfork.nucleoplasm.api.NucleoplasmServer.server();
        }
    }
}
