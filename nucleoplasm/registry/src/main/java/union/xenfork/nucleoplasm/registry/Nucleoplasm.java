package union.xenfork.nucleoplasm.registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import union.xenfork.nucleoplasm.registry.entrypoints.*;
import union.xenfork.nucleoplasm.registry.registry.ModBlocks;
import union.xenfork.nucleoplasm.registry.registry.ModGroups;
import union.xenfork.nucleoplasm.registry.registry.ModItems;

import static union.xenfork.nucleoplasm.registry.impl.ModBlock.modBlock;
import static union.xenfork.nucleoplasm.registry.impl.ModBlockAndItem.modBlockAndItem;
import static union.xenfork.nucleoplasm.registry.impl.ModGroup.modGroup;
import static union.xenfork.nucleoplasm.registry.impl.ModItem.modItem;

public class Nucleoplasm implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricLoader instance = FabricLoader.getInstance();
        var registry1 = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryAllEntrypoints.class);
        var registryItem = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryItemEntrypoints.class);
        var registryBlock = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryBlockEntrypoints.class);
        var registryBlockAndItem = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryBlockAndItemEntrypoints.class);
        var registryGroup = instance.getEntrypointContainers("nucleoplasm_registry:registry", RegistryGroupEntrypoints.class);

        for (var registry : registry1) {
            RegistryAllEntrypoints entrypoint = registry.getEntrypoint();
            entrypoint.registry(modBlockAndItem);
            entrypoint.registry(modBlock);
            entrypoint.registry(modItem);
            entrypoint.registry(modGroup);
        }
        for (EntrypointContainer<RegistryItemEntrypoints> registry : registryItem)
            registry.getEntrypoint().registry(modItem);
        for (var registry : registryBlock)
            registry.getEntrypoint().registry(modBlock);
        for (var registry : registryBlockAndItem)
            registry.getEntrypoint().registry(modBlockAndItem);
        for (var registry : registryGroup) {
            registry.getEntrypoint().registry(modGroup);
        }
        ModBlocks.registry();
        ModItems.registry();
        ModGroups.registry();
    }
}
