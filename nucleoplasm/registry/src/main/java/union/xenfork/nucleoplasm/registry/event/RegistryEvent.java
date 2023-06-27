package union.xenfork.nucleoplasm.registry.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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

    public static final Event<RegistryBlock> REGISTRY_BLOCK_EVENT = EventFactory.createArrayBacked(
            RegistryBlock.class,
            registryBlocks -> modBlock -> {
                for (RegistryBlock registryBlock : registryBlocks) {
                    registryBlock.registry(modBlock);
                }
            }
    );

    public static final Event<RegistryBlockAndItem> REGISTRY_BLOCK_AND_ITEM_EVENT =  EventFactory.createArrayBacked(
            RegistryBlockAndItem.class,
            registryBlockAndItems -> blockAndItem -> {
                for (RegistryBlockAndItem registryBlockAndItem : registryBlockAndItems) {
                    registryBlockAndItem.registry(blockAndItem);
                }
            }
    );

    public interface RegistryBlockAndItem {
        void registry(ModBlockAndItem blockAndItem);
    }

    public interface RegistryBlock {
        void registry(ModBlock modBlock);
    }

    public interface RegistryItem {
        void registry(ModItem modItem);
    }

    public static class ModItem {
        public final Map<String, Function<Item.Settings, Item>> map = new HashMap<>();
        public void add(String name, Function<Item.Settings, Item> function) {
            map.put(name, function);
        }
    }

    public static class ModBlock {
        public final Map<String, Function<AbstractBlock.Settings, Block>> map = new HashMap<>();
        public void add(String name, Function<AbstractBlock.Settings, Block> function) {
            map.put(name, function);
        }
    }

    public static class ModBlockAndItem {
        public final Map<String, Function<AbstractBlock.Settings, Block>> map = new HashMap<>();
        public final Map<String, Item.Settings> settingsMap = new HashMap<>();
        public void add(String name, Function<AbstractBlock.Settings, Block> function, Item.Settings settings) {
            map.put(name, function);
            settingsMap.put(name, settings);
        }
    }
}
