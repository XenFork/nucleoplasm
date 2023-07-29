package io.github.xenfork.nucleoplasm.core.item;

import com.google.gson.JsonElement;
import io.github.xenfork.nucleoplasm.core.SerializerImpl;
import io.github.xenfork.nucleoplasm.core.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class Inorganic$Item extends Item {
    /**
     * @apiNote 无机物
     * @param settings settings
     */
    public Inorganic$Item(Settings settings) {
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
        Serializer serializer = new Serializer(nbt);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= serializer.count; i++) {
            String an = serializer.nucleoplasm.getString("an_" + i);
            int count = serializer.nucleoplasm.getInt("count_" + i);
            if (!Utils.others.contains(an)) {
                sb.append(an).append(Utils.nums[count]);
            } else {
                sb.append("(").append(an).append(")").append(Utils.nums[count]);
            }
        }
        tooltip.add(Text.translatable("nucleoplasm.chemical.formula").append(sb.toString()));

    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains("nucleoplasm")) {
            return super.getTranslationKey(stack);
        }
        Serializer serializer = new Serializer(nbt);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= serializer.count; i++) {
            String an = serializer.nucleoplasm.getString("an_" + i);
            int count = serializer.nucleoplasm.getInt("count_" + i);
            if (!Utils.others.contains(an)) {
                sb.append(an).append(Utils.nums[count]);
            } else {
                sb.append("(").append(an).append(")").append(Utils.nums[count]);
            }
        }
        return super.getTranslationKey(stack) + "." + sb;
    }

    public static class Serializer implements SerializerImpl {
        NbtCompound nucleoplasm;
        int count;//决定了有多少物质组成
        /*
        300为OH-离子
         */

        public Serializer(NbtCompound nbt) {
            nucleoplasm = nbt.contains("nucleoplasm") ? nbt.getCompound("nucleoplasm") : new NbtCompound();
            count = nucleoplasm.contains("count") ? nbt.getInt("count") : 0;
        }

        public void add(String an, int count) {// 原子序数,和个数
            this.count++;
            nucleoplasm.putString("an_" + this.count, an);
            nucleoplasm.putInt("count_" + this.count, count);
        }

        public void clear() {
            for (int i = count; i > 0; i--) {
                nucleoplasm.remove("an_" +count);
                nucleoplasm.remove("count_" +count);
            }
            count = 0;
        }

        @Override
        public void deserializer(@NotNull NbtCompound nbt) {
            nucleoplasm.putInt("count", count);
            nbt.put("nucleoplasm", nucleoplasm);
        }

        @Override
        public NbtCompound getNbt() {
            return nucleoplasm.copy();
        }
    }

}
