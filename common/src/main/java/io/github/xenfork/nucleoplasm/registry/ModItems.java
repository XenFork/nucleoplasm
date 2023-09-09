package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.item.InorganicItem;
import io.github.xenfork.nucleoplasm.core.item.OrganicMatterItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.Nucleoplasm.items;

public enum ModItems implements Supplier<Item> {
    Inorganic(InorganicItem::new),
    Organic$Matter(OrganicMatterItem::new),
    ;


//    public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);


    public final Identifier id;
    public final Supplier<Item> item;
    public RegistrySupplier<Item> registry;

    ModItems(Function<Item.Settings, Item> item) {
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
        this.item = () -> item.apply(new Item.Settings());
    }

    public static void init() {
        for (ModItems value : values()) {
            value.registry = items.register(value.id, value.item);
        }
    }

    @Override
    public Item get() {
        return registry.get();
    }
}
