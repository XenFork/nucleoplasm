package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum Groups {
    elements(() -> new ItemStack(Items.Inorganic.get()));
    final ItemGroup builder;
    final String name;

    final DefaultedList<ItemStack> stacks = DefaultedList.of();
    RegistryKey<ItemGroup> registryKey;

    Groups(Supplier<ItemStack> stack) {
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        builder = CreativeTabRegistry.create(Text.translatable("nucleoplasm.group." + name), stack);

    }

    public void add(ItemStack stack) {
        stacks.add(stack);
    }

    public static final DeferredRegister<ItemGroup> groups = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);

    public static void init() {
        for (Groups value : values()) {
            groups.register(value.name().replace("$", "_").toLowerCase(Locale.ROOT), () -> value.builder);
            Optional<RegistryKey<ItemGroup>> key = groups.getRegistrar().getKey(value.getBuilder());
            value.registryKey = key.orElse(ItemGroups.BUILDING_BLOCKS);
        }
        groups.register();
    }

    public ItemGroup getBuilder() {
        return builder;
    }

    public RegistryKey<ItemGroup> getRegistryKey() {
        return registryKey;
    }
}
