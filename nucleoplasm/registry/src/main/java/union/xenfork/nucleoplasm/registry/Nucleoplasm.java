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

import static union.xenfork.nucleoplasm.registry.Record.*;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricLoader instance = FabricLoader.getInstance();
        var registryItem = instance.getEntrypointContainers("registryItem", RegistryItemEntrypoints.class);
        var registryBlock = instance.getEntrypointContainers("registryBlock", RegistryBlockEntrypoints.class);
        var registryBlockAndItem = instance.getEntrypointContainers("registryBlockAndItem", RegistryBlockAndItemEntrypoints.class);
        var registry1 = instance.getEntrypointContainers("registry", RegistryAllEntrypoints.class);
        for (var registry : registry1)
            registry.getEntrypoint().registry(modBlockAndItem, modBlock, modItem);
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
