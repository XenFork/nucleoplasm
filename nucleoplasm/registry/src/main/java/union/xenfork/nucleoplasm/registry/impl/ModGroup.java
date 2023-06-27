package union.xenfork.nucleoplasm.registry.impl;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class ModGroup extends ModDef<String, Function<ItemStack, ItemGroup.Builder>> {
    public static final ModGroup modGroup = new ModGroup();
}
