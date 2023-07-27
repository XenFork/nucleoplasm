package io.github.xenfork.nucleoplasm.core.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class Inorganic$Matter$Item extends Item {
    public Inorganic$Matter$Item(Settings settings) {
        super(settings.maxCount(32));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public static class Deserializer {
    }

    public static class Serializer {
    }

}
