package union.xenfork.nucleoplasm.api.fabric;

import net.fabricmc.api.ModInitializer;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        union.xenfork.nucleoplasm.api.Nucleoplasm.all();

    }
}
