package io.github.xenfork.nucleoplasm;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import io.github.xenfork.nucleoplasm.registry.ModFeatures;
import io.github.xenfork.nucleoplasm.registry.ModGroups;
import io.github.xenfork.nucleoplasm.registry.ModRecipes;
import io.github.xenfork.nucleoplasm.registry.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
	public static final DeferredRegister<Block> blocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);
	public static final DeferredRegister<Fluid> fluids = DeferredRegister.create(MOD_ID, RegistryKeys.FLUID);
	public static final DeferredRegister<ItemGroup> groups = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);
	public static final DeferredRegister<RecipeSerializer<?>> recipes = DeferredRegister.create(MOD_ID, RegistryKeys.RECIPE_SERIALIZER);
	public static final DeferredRegister<Feature<?>> features = DeferredRegister.create(MOD_ID, RegistryKeys.FEATURE);
	public static void init() {
		ModRegistry.init();
		ModGroups.init();
		ModRecipes.init();
		ModFeatures.init();
	}
}
