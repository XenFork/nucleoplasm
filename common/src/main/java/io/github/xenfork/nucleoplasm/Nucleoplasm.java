package io.github.xenfork.nucleoplasm;

import io.github.xenfork.nucleoplasm.registry.Blocks;
import io.github.xenfork.nucleoplasm.registry.Fluids;
import io.github.xenfork.nucleoplasm.registry.Groups;
import io.github.xenfork.nucleoplasm.registry.Items;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static void init() {
		Fluids.init();//注册流体在注册桶之前注册
		Blocks.init();//注册方块
		Items.init();//注册物品
		Groups.init();//注册组
	}
}
