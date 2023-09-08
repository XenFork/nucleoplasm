package io.github.xenfork.nucleoplasm;

import dev.architectury.registry.registries.DeferredRegister;
import io.github.xenfork.nucleoplasm.registry.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static void init() {
		Fluids.init();//注册流体在注册桶之前注册
		ModBlocks.init();//注册方块
		ModItems.init();//注册物品


		Groups.init();//注册组
		Recipes.init();//注册配方

	}
}
