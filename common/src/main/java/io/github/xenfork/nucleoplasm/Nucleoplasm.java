package io.github.xenfork.nucleoplasm;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeature;
import io.github.xenfork.nucleoplasm.core.gen.BrokenStoneFeatureConfig;
import io.github.xenfork.nucleoplasm.registry.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static final DeferredRegister<Item> items = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM);
	public static final DeferredRegister<Block> blocks = DeferredRegister.create(MOD_ID, RegistryKeys.BLOCK);
	public static final DeferredRegister<Fluid> fluids = DeferredRegister.create(MOD_ID, RegistryKeys.FLUID);
	public static final DeferredRegister<ItemGroup> groups = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);
	public static final DeferredRegister<RecipeSerializer<?>> recipes = DeferredRegister.create(MOD_ID, RegistryKeys.RECIPE_SERIALIZER);

	public static final DeferredRegister<Feature<?>> features = DeferredRegister.create(MOD_ID, RegistryKeys.FEATURE);

	public static DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatures;
	public static void init() {
		ModRegistry.init();
		ModGroups.init();
		ModRecipes.init();
		OtherRegister.init();
		ModFeatures.init();
		ModWorldGenerations.init();

	}
}
