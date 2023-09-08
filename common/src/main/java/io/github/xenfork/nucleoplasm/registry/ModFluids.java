package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.*;
import static io.github.xenfork.nucleoplasm.registry.ModItems.items;

public enum ModFluids implements Supplier<Fluid>, ItemConvertible {

    ;

    public static final DeferredRegister<Fluid> fluids = DeferredRegister.create(MOD_ID, RegistryKeys.FLUID);
    private final String name;
    private Fluid fluid;
    private Item.Settings settings;
    public RegistrySupplier<Fluid> register;
    public RegistrySupplier<BucketItem> bucketItem;
    ModFluids(Fluid fluid, Item.Settings settings) {
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.fluid = fluid;
        this.settings = settings;
    }



    public static void init() {
        for (ModFluids value : values()) {
            value.register = fluids.register(value.name, () -> value.fluid);
        }
        fluids.register();
        for (ModFluids value : values()) {
            value.bucketItem = items.register(value.name + "_bucket", () -> new BucketItem(value.get(), value.settings));
        }

    }

    @Override
    public Fluid get() {
        return register.get();
    }

    @Override
    public BucketItem asItem() {
        return bucketItem.get();
    }
}
