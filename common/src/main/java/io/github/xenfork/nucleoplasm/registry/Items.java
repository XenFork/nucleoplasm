package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.item.Inorganic$Item;
import io.github.xenfork.nucleoplasm.core.item.Organic$Matter$Item;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum Items implements Supplier<Item> {
    Inorganic(Inorganic$Item::new),
    Organic$Matter(Organic$Matter$Item::new),
    ;
    public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
    private final String name;
    private final Supplier<Item> item;
    public RegistrySupplier<Item> registry;
    Items(Function<Item.Settings, Item> item) {
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.item = () -> item.apply(new Item.Settings());
    }

    public static void init() {
        for (Items value : values()) {
            value.registry = items.register(value.name, value.item);
        }
        items.register();
    }

    @Override
    public Item get() {
        return item.get();
    }
}
