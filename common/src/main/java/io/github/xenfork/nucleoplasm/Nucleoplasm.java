package io.github.xenfork.nucleoplasm;

import dev.architectury.registry.CreativeTabRegistry;
import io.github.xenfork.nucleoplasm.core.Utils;
import io.github.xenfork.nucleoplasm.core.item.Inorganic$Item;
import io.github.xenfork.nucleoplasm.core.item.Organic$Matter$Item;
import io.github.xenfork.nucleoplasm.registry.Groups;
import io.github.xenfork.nucleoplasm.registry.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Arrays;

import static io.github.xenfork.nucleoplasm.core.Utils.nums;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static void init() {
		Items.init();
		Groups.init();
		CreativeTabRegistry.modify(CreativeTabRegistry.defer(Groups.elements.getRegistryKey()), (flags, output, canUseGameMasterBlocks) -> {
			Item item = Registries.ITEM.get(new Identifier(MOD_ID, "inorganic"));
			output.add(elemental(item,"H", nums[2]));
			output.add(elemental(item,"He"));
			output.add(elemental(item,"Li"));
			output.add(elemental(item,"Be"));
			output.add(elemental(item,"B", nums[2]));
			output.add(elemental(item,"B", nums[1], nums[0]));
			output.add(elemental(item,"B", nums[1], nums[2]));
			output.add(elemental(item,"B", nums[4], nums[0]));
			output.add(elemental(item, "C", nums[1]));
			output.add(elemental(item, "C", nums[6], nums[0]));
			output.add(elemental(item, "C", nums[3], nums[6]));
			output.add(elemental(item, "C", nums[7], nums[0]));
			output.add(elemental(item, "C", nums[8], nums[4]));
			output.add(elemental(item, "C", nums[2], nums[4], nums[0]));
			output.add(elemental(item, "C", nums[5], nums[4], nums[0]));
		});
	}

	public static ItemStack elemental(Item item, String... cfs) {
		ItemStack itemStack = new ItemStack(item);
		NbtCompound nbt = new NbtCompound();
		nbt.putString("cf", Arrays.toString(cfs));
		itemStack.setNbt(nbt);
		return itemStack;
	}
}
