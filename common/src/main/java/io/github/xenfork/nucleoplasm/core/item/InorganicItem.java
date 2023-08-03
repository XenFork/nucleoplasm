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

import static io.github.xenfork.nucleoplasm.core.Utils.nums;

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
            tooltip.add(translatable.append(Text.of(string.replace("0", nums[0]).replace("1", nums[1]).replace("2", nums[2]).replace("3", nums[3]).replace("4", nums[4]).replace("5", nums[5]).replace("6", nums[6]).replace("7", nums[7]).replace("8", nums[8]).replace("9", nums[9]))));

        }
    }

    @Override
    public Text getName(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains("cf")) {
            return super.getName(stack);
        }
        String string = nbt.getString("cf");
        if (string.startsWith("C")) {
            String c = string.replace("C", "");
            if (!c.matches("[a-zA-Z]+") && !c.isEmpty()) {
                try {
                    int i = Integer.parseInt(c);
                    if (i >= 4000) {
                        return Text.empty().append("[").append(String.valueOf(i)).append("]").append(Text.translatable("nucleoplasm.carbon.nano.foam"));
                    } else if (i >= 60) {
                        return Text.empty().append("[").append(String.valueOf(i)).append("]").append(Text.translatable("nucleoplasm.fullerene"));
                    }
                } catch (NumberFormatException ignored) {}

            }
        }
        return Text.translatable("nucleoplasm." + string);
    }
}
