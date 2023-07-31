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

import static io.github.xenfork.nucleoplasm.core.Utils.elements;
import static io.github.xenfork.nucleoplasm.core.Utils.nums;

public class Nucleoplasm
{
	public static final String MOD_ID = "nucleoplasm";

	public static void init() {
		Items.init();
		Groups.init();
		CreativeTabRegistry.modify(CreativeTabRegistry.defer(Groups.elements.getRegistryKey()), (flags, output, canUseGameMasterBlocks) -> {
			Item item = Registries.ITEM.get(new Identifier(MOD_ID, "inorganic"));
			output.add(elemental(item, elements[1], nums[2]));
			output.add(elemental(item,elements[1], nums[3]));
			output.add(elemental(item,elements[2], nums[2]));
			output.add(elemental(item,elements[2], nums[3]));
			output.add(elemental(item,elements[3]));
			output.add(elemental(item,elements[4]));
			output.add(elemental(item,elements[5], nums[2]));
			output.add(elemental(item,elements[5], nums[1], nums[0]));
			output.add(elemental(item,elements[5], nums[1], nums[2]));
			output.add(elemental(item,elements[5], nums[4], nums[0]));
			output.add(elemental(item, elements[6], nums[1]));
			output.add(elemental(item, elements[6], nums[6], nums[0]));
			output.add(elemental(item, elements[6], nums[3], nums[6]));
			output.add(elemental(item, elements[6], nums[7], nums[0]));
			output.add(elemental(item, elements[6], nums[8], nums[4]));
			output.add(elemental(item, elements[6], nums[2], nums[4], nums[0]));
			output.add(elemental(item, elements[6], nums[5], nums[4], nums[0]));
			output.add(elemental(item, elements[7], nums[2]));//氮气
			output.add(elemental(item, elements[7], nums[3]));//三氮
			output.add(elemental(item, elements[7], nums[4]));//四环氮
			output.add(elemental(item, elements[7], nums[5]));//5环氮
			output.add(elemental(item, elements[7], nums[6]));//6环氮
			output.add(elemental(item, elements[8], nums[2]));
			output.add(elemental(item, elements[8]));//自由氧
			output.add(elemental(item, elements[8], nums[3]));//臭氧
			output.add(elemental(item, elements[8], nums[4]));//四聚氧
			output.add(elemental(item, elements[8], nums[8]));//红氧
			output.add(elemental(item, elements[9], nums[2]));
			output.add(elemental(item, elements[10]));
			output.add(elemental(item, elements[11]));
			output.add(elemental(item, elements[12]));
			output.add(elemental(item, elements[13]));
			output.add(elemental(item, elements[14]));
			output.add(elemental(item, elements[15]));
			output.add(elemental(item, elements[16], nums[2]));
			output.add(elemental(item, elements[16], nums[3]));//三聚硫
			output.add(elemental(item, elements[16], nums[4]));//四聚硫
			output.add(elemental(item, elements[16], nums[5]));//环五硫
			output.add(elemental(item, elements[16], nums[6]));//环六硫
			output.add(elemental(item, elements[16], nums[6], "S", nums[10]));//环六环十硫加合物
			output.add(elemental(item, elements[16], nums[7]));//环七硫
			output.add(elemental(item, elements[16], nums[8]));//环八硫
			output.add(elemental(item, elements[17], nums[2]));
			output.add(elemental(item, elements[17], nums[3]));
			output.add(elemental(item, elements[17], nums[4]));
			output.add(elemental(item, elements[18]));
			output.add(elemental(item, elements[19]));
			output.add(elemental(item, elements[20]));
			output.add(elemental(item, elements[21]));
			output.add(elemental(item, elements[22]));
			output.add(elemental(item, elements[23]));
			output.add(elemental(item, elements[24]));
			output.add(elemental(item, elements[25]));
			output.add(elemental(item, elements[26]));
			output.add(elemental(item, elements[27]));
			output.add(elemental(item, elements[28]));
			output.add(elemental(item, elements[29]));
			output.add(elemental(item, elements[30]));
			output.add(elemental(item, elements[31]));
			output.add(elemental(item, elements[32]));
			output.add(elemental(item, elements[33]));
			output.add(elemental(item, elements[33], nums[4]));
			output.add(elemental(item, elements[34]));
			output.add(elemental(item, elements[34], nums[2]));
			output.add(elemental(item, elements[34], nums[8]));
			output.add(elemental(item, elements[34], nums[1000]));
			output.add(elemental(item, elements[35], nums[2]));
			output.add(elemental(item, elements[36]));
			output.add(elemental(item, elements[37]));
			output.add(elemental(item, elements[38]));
			output.add(elemental(item, elements[39]));
			output.add(elemental(item, elements[40]));
			output.add(elemental(item, elements[41]));
			output.add(elemental(item, elements[42]));
			output.add(elemental(item, elements[43]));
			output.add(elemental(item, "Ru"));
			output.add(elemental(item, "Rh"));
			output.add(elemental(item, "Pd"));
		});
	}

	public static ItemStack elemental(Item item, String... cfs) {
		ItemStack itemStack = new ItemStack(item);
		NbtCompound nbt = new NbtCompound();
		StringBuilder sb = new StringBuilder();
		for (String cf : cfs) {
			sb.append(cf);
		}
		nbt.putString("cf", sb.toString());
		nbt.putString("record", Arrays.toString(cfs));//用于存储记录分段
		itemStack.setNbt(nbt);
		return itemStack;
	}
}
