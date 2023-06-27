package union.xenfork.nucleoplasm.registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import union.xenfork.nucleoplasm.registry.entrypoints.RegistryAllEntrypoints;
import union.xenfork.nucleoplasm.registry.entrypoints.RegistryBlockAndItemEntrypoints;
import union.xenfork.nucleoplasm.registry.entrypoints.RegistryBlockEntrypoints;
import union.xenfork.nucleoplasm.registry.entrypoints.RegistryItemEntrypoints;
import union.xenfork.nucleoplasm.registry.registry.ModBlocks;
import union.xenfork.nucleoplasm.registry.registry.ModGroups;
import union.xenfork.nucleoplasm.registry.registry.ModItems;

import static union.xenfork.nucleoplasm.registry.impl.ModBlock.modBlock;
import static union.xenfork.nucleoplasm.registry.impl.ModBlockAndItem.modBlockAndItem;
import static union.xenfork.nucleoplasm.registry.impl.ModItem.modItem;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricLoader instance = FabricLoader.getInstance();
        var registryItem = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryItemEntrypoints.class);
        var registryBlock = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryBlockEntrypoints.class);
        var registryBlockAndItem = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryBlockAndItemEntrypoints.class);
        var registry1 = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryAllEntrypoints.class);
        for (var registry : registry1) {
            RegistryAllEntrypoints entrypoint = registry.getEntrypoint();
            entrypoint.registry(modBlockAndItem);
            entrypoint.registry(modBlock);
            entrypoint.registry(modItem);
        }
        for (EntrypointContainer<RegistryItemEntrypoints> registry : registryItem)
            registry.getEntrypoint().registry(modItem);
        for (var registry : registryBlock)
            registry.getEntrypoint().registry(modBlock);
        for (var registry : registryBlockAndItem)
            registry.getEntrypoint().registry(modBlockAndItem);
        ModBlocks.registry();
        ModItems.registry();
        ModGroups.registry();
    }
}
