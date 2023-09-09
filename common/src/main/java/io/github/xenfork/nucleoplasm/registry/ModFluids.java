package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.*;

public enum ModFluids implements Supplier<Fluid>, ItemConvertible {

    ;


    public final Identifier id;
    public Fluid fluid;
    public Item.Settings settings;
    public RegistrySupplier<Fluid> register;
    public RegistrySupplier<BucketItem> bucketItem;
    ModFluids(Fluid fluid, Item.Settings settings) {
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
        this.fluid = fluid;
        this.settings = settings;
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
