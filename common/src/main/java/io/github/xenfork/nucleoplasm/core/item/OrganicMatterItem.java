package io.github.xenfork.nucleoplasm.core.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OrganicMatterItem extends Item {
    /**
     * @apiNote 有机物
     * @param settings settings
     */
    public OrganicMatterItem(Settings settings) {
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
