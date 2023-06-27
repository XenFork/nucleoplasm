package union.xenfork.nucleoplasm.registry.entrypoints;

import union.xenfork.nucleoplasm.registry.impl.ModBlock;
import union.xenfork.nucleoplasm.registry.impl.ModBlockAndItem;
import union.xenfork.nucleoplasm.registry.impl.ModItem;

public interface RegistryAllEntrypoints {
    void registry(ModBlockAndItem modBlockAndItem, ModBlock modBlock, ModItem modItem);
}
