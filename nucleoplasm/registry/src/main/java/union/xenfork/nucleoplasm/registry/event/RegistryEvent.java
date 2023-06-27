package union.xenfork.nucleoplasm.registry.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RegistryEvent {

    public static final Event<RegistryItem> REGISTRY_ITEM_EVENT = EventFactory.createArrayBacked(
            RegistryItem.class,
            registryItems -> modItem -> {
                for (RegistryItem registryItem : registryItems) {
                    registryItem.registry(modItem);
                }
            }
    );
    public interface RegistryItem {
        void registry(ModItem modItem);
    }

    public static class ModItem {
        public final Map<String, Function<Item.Settings, Item>> map = new HashMap<>();
        public void add(String name, Function<Item.Settings, Item> function) {
            map.put(name, function);
        }
    }
}
