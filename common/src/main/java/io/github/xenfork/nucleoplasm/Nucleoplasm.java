package io.github.xenfork.nucleoplasm;

import dev.architectury.registry.registries.DeferredRegister;
import io.github.xenfork.nucleoplasm.registry.Items;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.List;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static void init() {
		Items.init();
	}
}
