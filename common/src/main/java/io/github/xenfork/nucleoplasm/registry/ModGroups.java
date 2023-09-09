package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.Nucleoplasm.groups;
import static io.github.xenfork.nucleoplasm.core.Utils.ELEMENT_NAMES;

public enum ModGroups {
    elements(() -> new ItemStack(ModRegistry.Inorganic.asItem()), ModGroups::addElements),
    ;

    public final ItemGroup builder;
    final Identifier id;
    public final CreativeTabRegistry.ModifyTabCallback cb;

    public RegistrySupplier<ItemGroup> value;

    ModGroups(Supplier<ItemStack> stack, CreativeTabRegistry.ModifyTabCallback cb) {
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
        builder = CreativeTabRegistry.create(Text.translatable("nucleoplasm.group." + id.getPath()), stack);
        Objects.requireNonNull(cb);
        this.cb = cb;
    }

    private static void addElements(FeatureSet flags, ItemGroup.Entries output, boolean canUseGameMasterBlocks) {
        Item item = ModRegistry.Inorganic.asItem();
        output.add(elemental(item, ELEMENT_NAMES[1]));//金属氢
        output.add(elemental(item, ELEMENT_NAMES[1] + 2));
        output.add(elemental(item, ELEMENT_NAMES[1] + 3));

        output.add(elemental(item, ELEMENT_NAMES[2] + 2));
        output.add(elemental(item, ELEMENT_NAMES[2] + 3));

        output.add(elemental(item, ELEMENT_NAMES[3]));

        output.add(elemental(item, ELEMENT_NAMES[4]));

        output.add(elemental(item, ELEMENT_NAMES[5] + 2));
        output.add(elemental(item, ELEMENT_NAMES[5] + 10));
        output.add(elemental(item, ELEMENT_NAMES[5] + 12));
        output.add(elemental(item, ELEMENT_NAMES[5] + 40));

        output.add(elemental(item, ELEMENT_NAMES[6]));//碳
        output.add(elemental(item, ELEMENT_NAMES[6] + "h"));//碳纳米管通式
        output.add(elemental(item, ELEMENT_NAMES[6] + 2));//双原子碳
        output.add(elemental(item, ELEMENT_NAMES[6] + 3));//环丙三烯
        output.add(elemental(item, ELEMENT_NAMES[6] + 6));//苯三炔
        output.add(elemental(item, ELEMENT_NAMES[6] + 8));//碳8
        output.add(elemental(item, ELEMENT_NAMES[6] + 18));//环[18]碳
        output.add(elemental(item, ELEMENT_NAMES[6] + 20));//二十烷衍生物
        output.add(elemental(item, ELEMENT_NAMES[6] + 60));//[60]富勒烯
        output.add(elemental(item, ELEMENT_NAMES[6] + 36));
        output.add(elemental(item, ELEMENT_NAMES[6] + 70));
        output.add(elemental(item, ELEMENT_NAMES[6] + 84));
        output.add(elemental(item, ELEMENT_NAMES[6] + 240));
        output.add(elemental(item, ELEMENT_NAMES[6] + 540));
        output.add(elemental(item, ELEMENT_NAMES[6] + 4000));//碳纳米泡沫

        output.add(elemental(item, ELEMENT_NAMES[7] + 2));//氮气
        output.add(elemental(item, ELEMENT_NAMES[7] + 3));//三氮
        output.add(elemental(item, ELEMENT_NAMES[7] + 4));//四环氮
        output.add(elemental(item, ELEMENT_NAMES[7] + 5));//5环氮
        output.add(elemental(item, ELEMENT_NAMES[7] + 6));//6环氮

        output.add(elemental(item, ELEMENT_NAMES[8] + 2));
        output.add(elemental(item, ELEMENT_NAMES[8]));//自由氧
        output.add(elemental(item, ELEMENT_NAMES[8] + 3));//臭氧
        output.add(elemental(item, ELEMENT_NAMES[8] + 4));//四聚氧
        output.add(elemental(item, ELEMENT_NAMES[8] + 8));//红氧

        output.add(elemental(item, ELEMENT_NAMES[9] + 2));

        for (int i = 10; i <= 15; i++) {
            output.add(elemental(item, ELEMENT_NAMES[i]));
        }

        output.add(elemental(item, ELEMENT_NAMES[16] + 2));
        output.add(elemental(item, ELEMENT_NAMES[16] + 3));//三聚硫
        output.add(elemental(item, ELEMENT_NAMES[16] + 4));//四聚硫
        output.add(elemental(item, ELEMENT_NAMES[16] + 5));//环五硫
        output.add(elemental(item, ELEMENT_NAMES[16] + 6));//环六硫
        output.add(elemental(item, ELEMENT_NAMES[16] + 6 + "-" + ELEMENT_NAMES[16] + 10));//环六环十硫加合物
        output.add(elemental(item, ELEMENT_NAMES[16] + 7));//环七硫
        output.add(elemental(item, ELEMENT_NAMES[16] + 8));//环八硫

        output.add(elemental(item, ELEMENT_NAMES[17] + 2));
        output.add(elemental(item, ELEMENT_NAMES[17] + 3));
        output.add(elemental(item, ELEMENT_NAMES[17] + 4));
        for (int i = 18; i <= 34; i++) {
            output.add(elemental(item, ELEMENT_NAMES[i]));
        }
        output.add(elemental(item, ELEMENT_NAMES[33] + 4));

        output.add(elemental(item, ELEMENT_NAMES[34] + 2));
        output.add(elemental(item, ELEMENT_NAMES[34] + 8));
        output.add(elemental(item, ELEMENT_NAMES[34] + 1000));

        output.add(elemental(item, ELEMENT_NAMES[35] + 2));
        for (int i = 36;i<=52;i++) {
            output.add(elemental(item, ELEMENT_NAMES[i]));
        }
        output.add(elemental(item, ELEMENT_NAMES[53] + 2));
        for (int i = 54; i < ELEMENT_NAMES.length; i++) {
            output.add(elemental(item, ELEMENT_NAMES[i]));
        }
    }

    public static ItemStack elemental(Item item, String... cfs) {
        ItemStack itemStack = new ItemStack(item);
        NbtCompound nbt = new NbtCompound();
        StringBuilder sb = new StringBuilder();
        for (String cf : cfs) {
            sb.append(cf);
        }
        nbt.putString("cf", sb.toString());
        itemStack.setNbt(nbt);
        return itemStack;
    }

    public RegistrySupplier<ItemGroup> get() {
        return value;
    }

    public Identifier getId() {
        return id;
    }

    public static void init() {
        for (ModGroups group : ModGroups.values()) {
			group.value = groups.register(group.getId(), () -> group.builder);
			RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, group.getId());
			CreativeTabRegistry.modify(CreativeTabRegistry.defer(key), group.cb);
		}
        groups.register();
    }

}
