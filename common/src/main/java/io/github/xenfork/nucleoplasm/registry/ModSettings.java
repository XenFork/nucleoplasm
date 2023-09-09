package io.github.xenfork.nucleoplasm.registry;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.block.BrokenStoneBlock;
import io.github.xenfork.nucleoplasm.core.item.InorganicItem;
import io.github.xenfork.nucleoplasm.core.item.OrganicMatterItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.Nucleoplasm.manager;

public enum ModSettings implements Supplier<Object>, ItemConvertible {
    Broken$Stone(BrokenStoneBlock::new, new Item.Settings()),//破碎的石子
    Inorganic(InorganicItem::new),
    Organic$Matter(OrganicMatterItem::new),
    ;


    public RegistrySupplier<?> value;//item or more
    public RegistrySupplier<?> value2;//block or fuild or more

    ModSettings(Function<Item.Settings, Item> func) {
        manager.get().forRegistry(RegistryKeys.ITEM, items -> {
            value = items.register(id(), () -> func.apply(new Item.Settings()));
        });
    }

    @NotNull
    private String rename() {
        return name().replace("$", "_").toLowerCase(Locale.ROOT);
    }

    ModSettings(Function<AbstractBlock.Settings, Block> func, Item.Settings... settings) {
        var ref = new Object() {
            RegistrySupplier<Block> register;
        };
        manager.get().forRegistry(RegistryKeys.BLOCK, blocks -> {
            ref.register = blocks.register(id(), () -> func.apply(AbstractBlock.Settings.create()));
        });
        manager.get().forRegistry(RegistryKeys.ITEM, items -> {
            value2 = ref.register;
            value = items.register(id(), () -> new BlockItem(ref.register.get(), (settings.length == 0 ? new Item.Settings() : settings[0])));
        });
    }

    @NotNull
    private Identifier id() {
        return new Identifier(MOD_ID, rename());
    }

    @Override
    public Object get() {
        return value2.get();
    }
    //不能强行转非item类，注意get这个记得加try
    @Override
    public Item asItem() {
        return (Item) value.get();
    }
}
