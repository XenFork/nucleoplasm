package union.xenfork.nucleoplasm.api.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(union.xenfork.nucleoplasm.api.Nucleoplasm.MOD_ID)
public class Nucleoplasm {
    public Nucleoplasm() {
        EventBuses.registerModEventBus(union.xenfork.nucleoplasm.api.Nucleoplasm.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        union.xenfork.nucleoplasm.api.Nucleoplasm.all();
        union.xenfork.nucleoplasm.api.NucleoplasmServer.server();

    }
}
