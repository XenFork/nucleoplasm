package io.github.xenfork.nucleoplasm.core.item;

import com.google.gson.JsonElement;
import io.github.xenfork.nucleoplasm.core.SerializerImpl;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

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
