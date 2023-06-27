package union.xenfork.nucleoplasm.registry.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Function;

public enum ModItems {
    ;
    public final Identifier id;
    public final Item item;
    ModItems(Function<Item.Settings, Item> items) {
        id = new Identifier("nucleoplasm_registry", name().toLowerCase(Locale.ROOT));
        item = items.apply(new Item.Settings());
    }

    public static void registry() {
        for (ModItems value : values()) {
            Registry.register(Registries.ITEM, value.id , value.item);
        }
    }
}
