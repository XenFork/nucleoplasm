package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.item.InorganicItem;
import io.github.xenfork.nucleoplasm.core.item.OrganicMatterItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum ModItems implements Supplier<Item> {
    Inorganic(InorganicItem::new),
    Organic$Matter(OrganicMatterItem::new),
    ;


    public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);

    private final String name;
    private final Supplier<Item> item;
    public RegistrySupplier<Item> registry;

    ModItems(Function<Item.Settings, Item> item) {
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.item = () -> item.apply(new Item.Settings());
    }

    public static void init() {
        for (ModItems value : values()) {
            value.registry = items.register(value.name, value.item);
        }
        for (ModBlocks value : ModBlocks.values()) {
            if (value.isBlockItem) {
                Item.Settings t = new Item.Settings();
                value.settings.accept(t);
                value.blockItem = items.register(value.name + "_block", () -> new BlockItem(value.get(), t));
            }
        }
        items.register();
    }

    @Override
    public Item get() {
        return registry.get();
    }
}
