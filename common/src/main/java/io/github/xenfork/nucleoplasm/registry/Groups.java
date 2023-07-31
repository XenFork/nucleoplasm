package io.github.xenfork.nucleoplasm.registry;

import dev.architectury.registry.CreativeTabOutput;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.xenfork.nucleoplasm.core.Utils;
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

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static io.github.xenfork.nucleoplasm.Nucleoplasm.MOD_ID;
import static io.github.xenfork.nucleoplasm.core.Utils.ELEMENT_NAMES;
import static io.github.xenfork.nucleoplasm.core.Utils.nums;

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

    private static void addElements(FeatureSet flags, CreativeTabOutput output, boolean canUseGameMasterBlocks) {
        Item item = Items.Inorganic.get();
        output.add(elemental(item, ELEMENT_NAMES[1]));//金属氢
        output.add(elemental(item, ELEMENT_NAMES[1], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[1], nums[3]));
        output.add(elemental(item, ELEMENT_NAMES[2], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[2], nums[3]));
        output.add(elemental(item, ELEMENT_NAMES[3]));
        output.add(elemental(item, ELEMENT_NAMES[4]));
        output.add(elemental(item, ELEMENT_NAMES[5], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[5], nums[1], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[5], nums[1], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[5], nums[4], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[1]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[6], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[3], nums[6]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[7], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[8], nums[4]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[2], nums[4], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[6], nums[5], nums[4], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[7], nums[2]));//氮气
        output.add(elemental(item, ELEMENT_NAMES[7], nums[3]));//三氮
        output.add(elemental(item, ELEMENT_NAMES[7], nums[4]));//四环氮
        output.add(elemental(item, ELEMENT_NAMES[7], nums[5]));//5环氮
        output.add(elemental(item, ELEMENT_NAMES[7], nums[6]));//6环氮
        output.add(elemental(item, ELEMENT_NAMES[8], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[8]));//自由氧
        output.add(elemental(item, ELEMENT_NAMES[8], nums[3]));//臭氧
        output.add(elemental(item, ELEMENT_NAMES[8], nums[4]));//四聚氧
        output.add(elemental(item, ELEMENT_NAMES[8], nums[8]));//红氧
        output.add(elemental(item, ELEMENT_NAMES[9], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[10]));
        output.add(elemental(item, ELEMENT_NAMES[11]));
        output.add(elemental(item, ELEMENT_NAMES[12]));
        output.add(elemental(item, ELEMENT_NAMES[13]));
        output.add(elemental(item, ELEMENT_NAMES[14]));
        output.add(elemental(item, ELEMENT_NAMES[15]));
        output.add(elemental(item, ELEMENT_NAMES[16], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[16], nums[3]));//三聚硫
        output.add(elemental(item, ELEMENT_NAMES[16], nums[4]));//四聚硫
        output.add(elemental(item, ELEMENT_NAMES[16], nums[5]));//环五硫
        output.add(elemental(item, ELEMENT_NAMES[16], nums[6]));//环六硫
        output.add(elemental(item, ELEMENT_NAMES[16], nums[6], "S", nums[1], nums[0]));//环六环十硫加合物
        output.add(elemental(item, ELEMENT_NAMES[16], nums[7]));//环七硫
        output.add(elemental(item, ELEMENT_NAMES[16], nums[8]));//环八硫
        output.add(elemental(item, ELEMENT_NAMES[17], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[17], nums[3]));
        output.add(elemental(item, ELEMENT_NAMES[17], nums[4]));
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
        output.add(elemental(item, ELEMENT_NAMES[33], nums[4]));
        output.add(elemental(item, ELEMENT_NAMES[34]));
        output.add(elemental(item, ELEMENT_NAMES[34], nums[2]));
        output.add(elemental(item, ELEMENT_NAMES[34], nums[8]));
        output.add(elemental(item, ELEMENT_NAMES[34], nums[1], nums[0], nums[0], nums[0]));
        output.add(elemental(item, ELEMENT_NAMES[35], nums[2]));
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
        output.add(elemental(item, ELEMENT_NAMES[53], nums[2]));
        output.add(elemental(item, "Xe"));
        output.add(elemental(item, "Cs"));
        output.add(elemental(item, "Ba"));
        output.add(elemental(item, "La"));
        output.add(elemental(item, "Ce"));
        output.add(elemental(item, "Pr"));
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
        nbt.putString("record", Arrays.toString(cfs));//用于存储记录分段
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
