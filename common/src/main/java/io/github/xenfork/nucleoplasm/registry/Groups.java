package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
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
import net.minecraft.util.collection.DefaultedList;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.core.Utils.ELEMENT_NAMES;

public enum Groups {
    elements(() -> new ItemStack(Items.Inorganic.get()), Groups::addElements);

    final ItemGroup builder;
    final Identifier id;
    private final CreativeTabRegistry.ModifyTabCallback cb;

    RegistrySupplier<ItemGroup> value;

    final DefaultedList<ItemStack> stacks = DefaultedList.of();

    Groups(Supplier<ItemStack> stack, CreativeTabRegistry.ModifyTabCallback cb) {
        id = new Identifier(MOD_ID, name().replace("$", "_").toLowerCase(Locale.ROOT));
        builder = CreativeTabRegistry.create(Text.translatable("nucleoplasm.group." + id.getPath()), stack);
        Objects.requireNonNull(cb);
        this.cb = cb;
    }

    private static void addElements(FeatureSet flags, ItemGroup.Entries output, boolean canUseGameMasterBlocks) {
        Item item = Items.Inorganic.get();
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

        output.add(elemental(item, ELEMENT_NAMES[10]));
        output.add(elemental(item, ELEMENT_NAMES[11]));
        output.add(elemental(item, ELEMENT_NAMES[12]));
        output.add(elemental(item, ELEMENT_NAMES[13]));
        output.add(elemental(item, ELEMENT_NAMES[14]));
        output.add(elemental(item, ELEMENT_NAMES[15]));

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
        output.add(elemental(item, ELEMENT_NAMES[18]));
        output.add(elemental(item, ELEMENT_NAMES[19]));
        output.add(elemental(item, ELEMENT_NAMES[20]));
        output.add(elemental(item, ELEMENT_NAMES[21]));
        output.add(elemental(item, ELEMENT_NAMES[22]));
        output.add(elemental(item, ELEMENT_NAMES[23]));
        output.add(elemental(item, ELEMENT_NAMES[24]));
        output.add(elemental(item, ELEMENT_NAMES[25]));
        output.add(elemental(item, ELEMENT_NAMES[26]));
        output.add(elemental(item, ELEMENT_NAMES[27]));
        output.add(elemental(item, ELEMENT_NAMES[28]));
        output.add(elemental(item, ELEMENT_NAMES[29]));
        output.add(elemental(item, ELEMENT_NAMES[30]));
        output.add(elemental(item, ELEMENT_NAMES[31]));
        output.add(elemental(item, ELEMENT_NAMES[32]));

        output.add(elemental(item, ELEMENT_NAMES[33]));
        output.add(elemental(item, ELEMENT_NAMES[33] + 4));

        output.add(elemental(item, ELEMENT_NAMES[34]));
        output.add(elemental(item, ELEMENT_NAMES[34] + 2));
        output.add(elemental(item, ELEMENT_NAMES[34] + 8));
        output.add(elemental(item, ELEMENT_NAMES[34] + 1000));

        output.add(elemental(item, ELEMENT_NAMES[35] + 2));

        output.add(elemental(item, ELEMENT_NAMES[36]));
        output.add(elemental(item, ELEMENT_NAMES[37]));
        output.add(elemental(item, ELEMENT_NAMES[38]));
        output.add(elemental(item, ELEMENT_NAMES[39]));
        output.add(elemental(item, ELEMENT_NAMES[40]));
        output.add(elemental(item, ELEMENT_NAMES[41]));
        output.add(elemental(item, ELEMENT_NAMES[42]));
        output.add(elemental(item, ELEMENT_NAMES[43]));
        output.add(elemental(item, ELEMENT_NAMES[44]));
        output.add(elemental(item, ELEMENT_NAMES[45]));
        output.add(elemental(item, ELEMENT_NAMES[46]));
        output.add(elemental(item, ELEMENT_NAMES[47]));
        output.add(elemental(item, ELEMENT_NAMES[48]));
        output.add(elemental(item, ELEMENT_NAMES[49]));
        output.add(elemental(item, ELEMENT_NAMES[50]));
        output.add(elemental(item, ELEMENT_NAMES[51]));
        output.add(elemental(item, ELEMENT_NAMES[52]));
        output.add(elemental(item, ELEMENT_NAMES[53] + 2));
        output.add(elemental(item, ELEMENT_NAMES[54]));
        output.add(elemental(item, ELEMENT_NAMES[55]));
        output.add(elemental(item, ELEMENT_NAMES[56]));
        output.add(elemental(item, ELEMENT_NAMES[57]));
        output.add(elemental(item, ELEMENT_NAMES[58]));
        output.add(elemental(item, ELEMENT_NAMES[59]));
        output.add(elemental(item, "Nd"));
        output.add(elemental(item, "Pm"));
        output.add(elemental(item, "Sm"));
        output.add(elemental(item, "Eu"));
        output.add(elemental(item, "Gd"));
        output.add(elemental(item, "Tb"));
        output.add(elemental(item, "Dy"));
        output.add(elemental(item, "Ho"));
        output.add(elemental(item, "Er"));
        output.add(elemental(item, "Tm"));
        output.add(elemental(item, "Yb"));
        output.add(elemental(item, "Lu"));
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

    public void add(ItemStack stack) {
        stacks.add(stack);
    }

    public RegistrySupplier<ItemGroup> get() {
        return value;
    }

    public Identifier getId() {
        return id;
    }

    public static final DeferredRegister<ItemGroup> groups = DeferredRegister.create(MOD_ID, RegistryKeys.ITEM_GROUP);

    public static void init() {
        for (Groups group : values()) {
            group.value = groups.register(group.id.getPath(), () -> group.builder);
            RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, group.getId());
            CreativeTabRegistry.modify(CreativeTabRegistry.defer(key), group.cb);
        }
        groups.register();
    }

    public ItemGroup getBuilder() {
        return builder;
    }
}
