package union.xenfork.nucleoplasm.lib.fabric;

import net.fabricmc.api.ModInitializer;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        union.xenfork.nucleoplasm.lib.Nucleoplasm.init();
    }
}
