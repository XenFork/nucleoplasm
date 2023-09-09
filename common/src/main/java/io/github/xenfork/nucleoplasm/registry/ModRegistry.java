package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.block.BrokenStoneBlock;
import io.github.xenfork.nucleoplasm.core.item.InorganicItem;
import io.github.xenfork.nucleoplasm.core.item.OrganicMatterItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.*;

public enum ModRegistry implements Supplier<Object>, ItemConvertible {
    Broken$Stone(BrokenStoneBlock::new, new Item.Settings()),//破碎的石子
    Inorganic(InorganicItem::new),
    Organic$Matter(OrganicMatterItem::new),
    ;


    public final RegistrySupplier<?> value;//item or more
    public RegistrySupplier<?> value2;//block or more
    public RegistrySupplier<?> value3;// fuild or more
    public RegistrySupplier<?> value4;// still fluid or more;


    ModRegistry(Function<Item.Settings, Item> func) {
        value = items.register(id(), () -> func.apply(new Item.Settings()));
    }

    ModRegistry(Function<AbstractBlock.Settings, Block> func, Item.Settings... settings) {
        value2 = blocks.register(id(), () -> func.apply(AbstractBlock.Settings.create()));
        value = items.register(id(), () -> new BlockItem((Block) value2.get(), (settings.length == 0 ? new Item.Settings() : settings[0])));
    }

    ModRegistry(AbstractBlock.Settings block , Item.Settings item, @NotNull FlowableFluid fluid, FlowableFluid flowing) {
        value4 = fluids.register(id("still"), () -> fluid);
        value3 = fluids.register(id("flowing"), () -> fluid);
        value2 = blocks.register(id("fluid_block"), () -> new FluidBlock((FlowableFluid) value4.get(), block));
        value = items.register(id("bucket"), () -> new BucketItem(fluid, item));
    }

    public static void init() {
        fluids.register();
        blocks.register();
        items.register();
    }

    @NotNull
    private String rename() {
        return name().replace("$", "_").toLowerCase(Locale.ROOT);
    }


    @NotNull
    private Identifier id() {
        return new Identifier(MOD_ID, rename());
    }

    private Identifier id(String sub) {
        return new Identifier(MOD_ID, rename() + "_" + sub);
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
