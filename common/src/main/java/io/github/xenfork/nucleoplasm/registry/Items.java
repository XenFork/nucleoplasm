package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum Items implements Supplier<Item> {
    Inorganic$Matter(settings -> new Item(settings.maxCount(32))),
    Organic$Matter(settings -> new Item(settings.maxCount(32))),
    ;
    public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
    private final String name;
    private final Supplier<Item> item;
    Items(Function<Item.Settings, Item> item) {
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.item = () -> item.apply(new Item.Settings());
    }

    public static void init() {
        for (Items value : values()) {
            items.register(value.name, value.item);
        }
        items.register();
    }

    @Override
    public Item get() {
        return item.get();
    }
}
