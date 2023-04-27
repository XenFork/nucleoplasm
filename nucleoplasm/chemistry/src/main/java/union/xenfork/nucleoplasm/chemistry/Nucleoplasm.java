package union.xenfork.nucleoplasm.chemistry;

import net.fabricmc.api.ModInitializer;
import union.xenfork.nucleoplasm.chemistry.items.ModItems;

public class Nucleoplasm implements ModInitializer {
    public static final String modid = "nucleoplasm_chemistry";
    @Override
    public void onInitialize() {
        ModItems.registry();
    }
}
