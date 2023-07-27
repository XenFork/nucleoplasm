package io.github.xenfork.nucleoplasm.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.xenfork.nucleoplasm.Nucleoplasm;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Nucleoplasm.MOD_ID)
public class NucleoplasmForge {
    public NucleoplasmForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Nucleoplasm.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
            Nucleoplasm.init();
    }
}