package io.github.xenfork.nucleoplasm;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import io.github.xenfork.nucleoplasm.registry.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
	public static final DeferredRegister<Block> blocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);
	public static final DeferredRegister<Fluid> fluids = DeferredRegister.create(MOD_ID, RegistryKeys.FLUID);
	public static final DeferredRegister<ItemGroup> groups = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);
	public static final DeferredRegister<RecipeSerializer<?>> recipes = DeferredRegister.create(MOD_ID, RegistryKeys.RECIPE_SERIALIZER);

	public static void init() {
		ModSettings.init();
		blocks.register();
		items.register();
//		//注册流体在注册桶之前注册
//		for (ModFluids value : ModFluids.values()) {
//			value.register = fluids.register(value.id, () -> value.fluid);
//			value.bucketItem = items.register(new Identifier(MOD_ID, value.id.getPath() + "_bucket"), () -> new BucketItem(value.get(), value.settings));
//		}
//		//注册方块
//		for (ModBlocks value : ModBlocks.values()) {
//			value.register = blocks.register(value.id, () -> value.block);
//		}
//		//注册物品
//		for (ModItems value : ModItems.values()) {
//			value.registry = items.register(value.id, value.item);
//		}
//		for (ModGroups group : ModGroups.values()) {
//			group.value = groups.register(group.getId(), () -> group.builder);
//			RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, group.getId());
//			CreativeTabRegistry.modify(CreativeTabRegistry.defer(key), group.cb);
//		}
//		//注册配方
//		for (ModRecipes recipe : ModRecipes.values()) {
//			recipe.register = recipes.register(recipe.id, () -> recipe.serializer);
//		}
//		fluids.register();
//		blocks.register();
//		items.register();
//		groups.register();
//		recipes.register();
	}
}
