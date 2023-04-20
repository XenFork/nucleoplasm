package union.xenfork.nucleoplasm.api.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NucleoplasmApi.MOD_ID)
public class NucleoplasmApiForge {
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public NucleoplasmApiForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(NucleoplasmApi.MOD_ID, modEventBus);
        NucleoplasmApi.init();
    }
}