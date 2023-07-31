package io.github.xenfork.nucleoplasm.core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InorganicItem extends Item {
    /**
     * @apiNote 无机物
     * @param settings settings
     */
    public InorganicItem(Settings settings) {
        super(settings.maxCount(32));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) {
            return;
        }
        if (nbt.contains("cf")) {
            String string = nbt.getString("cf");
            MutableText translatable = Text.translatable("nucleoplasm.chemical.formula");
            MutableText translatable1 = Text.translatable("nucleoplasm.name");
            tooltip.add(translatable.append(Text.of(string)));
            tooltip.add(translatable1.append(Text.translatable("nucleoplasm." + string)));
            ;
        }



    }

}
