package union.xenfork.nucleoplasm.registry.impl;

import net.minecraft.item.Item;

import java.util.function.Function;

public class ModItem extends ModDef<String, Function<Item.Settings, Item>> {
    public static final ModItem modItem = new ModItem();
}
