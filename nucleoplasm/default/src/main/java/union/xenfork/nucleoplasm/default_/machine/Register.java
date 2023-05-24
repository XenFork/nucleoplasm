package union.xenfork.nucleoplasm.default_.machine;

import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static union.xenfork.nucleoplasm.default_.Nucleoplasm.MODID;

public class Register {

    public static final  CraftTableBlock craftingTableBlock = Registry.register(Registries.BLOCK, Identifier.of(MODID, "crafting_table_block"), new CraftTableBlock(AbstractBlock.Settings.create()));
    public static final BlockItem craftingTable = Registry.register(Registries.ITEM, Identifier.of(MODID, "crafting_table"), new BlockItem(craftingTableBlock, new Item.Settings()));



}
