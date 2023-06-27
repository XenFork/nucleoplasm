package union.xenfork.nucleoplasm.registry;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import union.xenfork.nucleoplasm.registry.event.RegistryEvent;
import union.xenfork.nucleoplasm.registry.registry.ModItems;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        RegistryEvent.REGISTRY_ITEM_EVENT.register(modItem -> {
            modItem.add("test", Item::new);
        });
        ModItems.registry();
    }
}
