package union.xenfork.nucleoplasm.registry;

import net.fabricmc.api.ModInitializer;
import union.xenfork.nucleoplasm.registry.registry.ModBlocks;
import union.xenfork.nucleoplasm.registry.registry.ModItems;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        ModBlocks.registry();
        ModItems.registry();
    }
}
