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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.registry.ModItems.items;

public enum ModBlocks implements Supplier<Block>, ItemConvertible {
    Broken$Stone(Block::new, true),//破碎的石子
    ;

    public static final DeferredRegister<Block> blocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);

    private final Block block;
    final String name;

    private RegistrySupplier<Block> register;
    Consumer<Item.Settings> settings;
    final boolean isBlockItem;
    RegistrySupplier<BlockItem> blockItem;
    ModBlocks(Function<AbstractBlock.Settings, Block> block, Consumer<Item.Settings> settings) {
        this.isBlockItem = true;
        this.block = block.apply(AbstractBlock.Settings.create());
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.settings = settings;
    }

    ModBlocks(Function<AbstractBlock.Settings, Block> block, boolean isBlockItem) {
        this.isBlockItem = isBlockItem;
        this.block = block.apply(AbstractBlock.Settings.create());
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
        this.settings = settings1 -> {};
    }

    ModBlocks(Function<AbstractBlock.Settings, Block> block) {
        this.isBlockItem = false;
        this.block = block.apply(AbstractBlock.Settings.create());
        name = name().replace("$", "_").toLowerCase(Locale.ROOT);
    }

    public static void init() {
        for (ModBlocks value : values()) {
            value.register = blocks.register(value.name, () -> value.block);
        }
        blocks.register();
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
