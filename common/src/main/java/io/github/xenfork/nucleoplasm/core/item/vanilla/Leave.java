package io.github.xenfork.nucleoplasm.core.item.vanilla;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Leave extends BlockItem {
    public Leave(Block block, Settings settings) {
        super(block, settings);
    }
    // C55H72MgN4O5 叶绿素a C55H70MgN4O6 叶绿素b


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!stack.hasNbt())
            stack.setNbt(new NbtCompound());
        NbtCompound nbt = stack.getNbt();
        assert nbt != null;
        if (!nbt.contains("chemical formula")) {
            nbt.putString("chemical formula", "C55H72MgN4O5,C55H70MgN4O6");
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (stack.getNbt() != null) {
            NbtCompound nbt = stack.getNbt();
            tooltip.add(Text.empty().append(Text.translatable("nucleoplasm.ingredients")).append(nbt.getString("chemical formula")));
        }
    }
}
