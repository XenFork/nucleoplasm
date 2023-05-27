package union.xenfork.nucleoplasm.default_.machine;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static union.xenfork.nucleoplasm.default_.Nucleoplasm.MODID;

public class Register {

    public static final Block craftingTableBlock;

    public static final BlockItem craftingTable;

    public static final BlockEntityType<CraftTableBlockEntity> craftTableBlockEntity;



    static {
        craftingTableBlock = Registry
            .register(
                Registries.BLOCK,
                Identifier.of(MODID, "crafting_table"),
                new CraftTableBlock(AbstractBlock.Settings.create())
            );
        craftingTable = Registry
            .register(
                Registries.ITEM,
                Identifier.of(MODID, "crafting_table"),
                new BlockItem(craftingTableBlock, new Item.Settings())
            );
        craftTableBlockEntity = Registry
            .register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MODID, "crafting_table_entity"),
                FabricBlockEntityTypeBuilder.create(CraftTableBlockEntity::new, craftingTableBlock).build()
            );
    }

    static <T extends Recipe<?>> RecipeType<T> register(final Identifier id) {
        return Registry.register(Registries.RECIPE_TYPE, id, new RecipeType<T>() {
            public String toString() {
                return id.toString();
            }
        });
    }

}
