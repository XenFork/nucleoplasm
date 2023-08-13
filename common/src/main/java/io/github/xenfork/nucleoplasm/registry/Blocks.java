package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;

public enum Blocks implements Supplier<Block>, ItemConvertible {
    ;

    public static final DeferredRegister<Block> blocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);

    private final Block block;
    private final String name;

    private RegistrySupplier<Block> register;
    private Item.Settings settings;
    private final boolean isBlockItem;
    private RegistrySupplier<BlockItem> blockItem;
    Blocks(Function<AbstractBlock.Settings, Block> block, Item.Settings settings) {
        this.isBlockItem = true;
        this.block = block.apply(AbstractBlock.Settings.create());
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.settings = settings;
    }

    Blocks(Function<AbstractBlock.Settings, Block> block, boolean isBlockItem) {
        this.isBlockItem = isBlockItem;
        this.block = block.apply(AbstractBlock.Settings.create());
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.settings = new Item.Settings();
    }

    Blocks(Function<AbstractBlock.Settings, Block> block) {
        this.isBlockItem = false;
        this.block = block.apply(AbstractBlock.Settings.create());
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
    }

    public static void init() {
        for (Blocks value : values()) {
            value.register = blocks.register(value.name, () -> value.block);
        }
        blocks.register();
        for (Blocks value : values()) {
            if (value.isBlockItem)
                value.blockItem = ModItems.items.register(value.name + "_block", () -> new BlockItem(value.get(), value.settings));
        }
    }

    @Override
    public Block get() {
        return register.get();
    }

    @Override
    public BlockItem asItem() {
        return blockItem.get();
    }
}
