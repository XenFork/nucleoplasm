package union.xenfork.nucleoplasm.registry.impl;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ModBlockAndItem extends ModBlock {
    public static final ModBlockAndItem modBlockAndItem = new ModBlockAndItem();
    public final Map<String, Item.Settings> settingsMap = new HashMap<>();

    public void add(String name, Function<AbstractBlock.Settings, Block> function, Item.Settings settings) {
        super.add(name, function);
        settingsMap.put(name, settings);
    }
}
