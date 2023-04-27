package union.xenfork.nucleoplasm.chemistry.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item1to10.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item11to20.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item21to30.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item31to40.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item41to50.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item51to60.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item61to70.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item71to80.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item81to90.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item91to100.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item101to110.*;
import union.xenfork.nucleoplasm.chemistry.items.extendItem.items.Item111to118.*;

import java.util.Locale;
import java.util.function.Function;

import static union.xenfork.nucleoplasm.chemistry.Nucleoplasm.modid;

public enum ModItems {
    h(HItem::new, 1, 7),
    he(HeItem::new, 3, 10),
    li(LiItem::new, 4, 12),
    be(BeItem::new, 6, 16),
    b(BItem::new, 7, 21),
    c(CItem::new, 8, 22),
    n(NItem::new, 10, 24),
    o(OItem::new, 12, 26),
    f(FItem::new, 14, 31),
    ne(NeItem::new, 15, 34),
    na(NaItem::new, 17, 37),
    mg(MgItem::new, 19, 40),
    al(AlItem::new, 21, 43),
    si(SiItem::new, 22, 44),
    p(PItem::new, 25, 46),
    s(SItem::new, 26, 48),
    cl(ClItem::new, 29, 51),
    ar(ArItem::new, 30, 53),
    k(KItem::new, 33, 56),
    ca(CaItem::new, 34, 58),
    sc(ScItem::new, 38, 61),
    ti(TiItem::new, 38, 64),
    v(VItem::new, 42, 67),
    cr(CrItem::new, 42, 70),
    mn(MnItem::new, 44, 73),
    fe(FeItem::new, 45, 75),
    co(CoItem::new, 50, 77),
    ni(NiItem::new, 48, 82),
    cu(CuItem::new, 53, 82),
    zn(ZnItem::new, 54, 85),
    ga(GaItem::new, 59, 87),
    ge(GeItem::new, 59, 90),
    as(AsItem::new, 63, 92),
    se(SeItem::new, 64, 95),
    br(BrItem::new, 69, 98),
    kr(KrItem::new, 69, 101),
    rb(RbItem::new, 73, 103),
    sr(SrItem::new, 73, 107),
    y(YItem::new, 76, 109),
    zr(ZrItem::new, 78, 112),
    nb(NbItem::new, 81, 115),
    mo(MoItem::new, 81, 118),
    tc(TcItem::new, 85, 121),
    ru(RuItem::new, 85, 124),
    rh(RhItem::new, 89, 127),
    pd(PdItem::new, 90, 129),
    ag(AgItem::new, 92, 132),
    cd(CdItem::new, 94, 134),
    in(InItem::new, 96, 137),
    sn(SnItem::new, 99, 139),
    sb(SbItem::new, 103, 141),
    te(TeItem::new, 105, 144),
    i(IItem::new, 108, 145),
    xe(XeItem::new, 109, 148),
    cs(CsItem::new, 112, 152),
    ba(BaItem::new, 114, 154),
    la(LaItem::new, 117, 156),
    ce(CeItem::new, 121, 158),
    pr(PrItem::new, 121, 160),
    nd(NdItem::new, 125, 162),
    pm(PmItem::new, 128, 163),
    sm(SmItem::new, 129, 166),
    eu(EuItem::new, 130, 168),
    gd(GdItem::new, 134, 170),
    tb(TbItem::new, 135, 172),
    dy(DyItem::new, 139, 174),
    ho(HoItem::new, 140, 177),
    er(ErItem::new, 143, 178),
    tm(TmItem::new, 144, 181),
    yb(YbItem::new, 149, 185),
    lu(LuItem::new, 150, 188),
    hf(HfItem::new, 151, 190),
    ta(TaItem::new, 155, 194),
    w(WItem::new, 157, 197),
    re(ReItem::new, 159, 199),
    os(OsItem::new, 161, 203),
    ir(IrItem::new, 164, 205),
    pt(PtItem::new, 166, 208),
    au(AuItem::new, 170, 210),
    hg(HgItem::new, 171, 216),
    tl(TlItem::new, 176, 217),
    pb(PbItem::new, 178, 220),
    bi(BiItem::new, 184, 224),
    po(PoItem::new, 186, 227),
    at(AtItem::new, 191, 229),
    rn(RnItem::new, 193, 229),
    fr(FrItem::new, 197, 233),
    ra(RaItem::new, 201, 234),
    ac(AcItem::new, 205, 236),
    th(ThItem::new, 208, 238),
    pa(PaItem::new, 211, 239),
    u(UItem::new, 215, 243),
    np(NpItem::new, 219, 244),
    pu(PuItem::new, 228, 247),
    am(AmItem::new, 223, 248),
    cm(CmItem::new, 233, 252),
    bk(BkItem::new, 233, 253),
    cf(CfItem::new, 237, 256),
    es(EsItem::new, 240, 257),
    fm(FmItem::new, 241, 259),
    md(MdItem::new, 245, 260),
    no(NoItem::new, 248, 262),
    lr(LrItem::new, 252, 266),
    rf(RfItem::new, 253, 267),
    db(DbItem::new, 255, 270),
    sg(SgItem::new, 258, 271),
    bh(BhItem::new, 260, 278),
    hs(HsItem::new, 263, 278),
    mt(MtItem::new, 266, 282),
    ds(DsItem::new, 267, 282),
    rg(RgItem::new, 272, 286),
    cn(CnItem::new, 277, 286),
    nh(NhItem::new, 278, 290),
    fl(FlItem::new, 284, 290),
    mc(McItem::new, 287, 290),
    lv(LvItem::new, 290, 294),
    ts(TsItem::new, 293, 294),
    og(TsItem::new, 294, 294),
    ;
    private final Item item;
    private final Identifier id;
    private final ItemGroup group;
    private final RegistryKey<ItemGroup> registryKey;
    private final int min_mass_num, max_mass_num;
    ModItems(Function<Item.Settings, Item> function, int min_mass_num, int max_mass_num) {
        item = function.apply(new Item.Settings());
        String name = name().toLowerCase(Locale.ROOT);
        id = new Identifier(modid, name);
        group = FabricItemGroup
                .builder()
                .displayName(Text.translatable("%s.%s".formatted(modid, name)))
                .icon(
                        () -> this.getItem().getDefaultStack()
                )
                .build();
        registryKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(modid, name));
        this.min_mass_num = min_mass_num;
        this.max_mass_num = max_mass_num;
    }

    public Item getItem() {
        return item;
    }

    @SuppressWarnings("unused")
    public RegistryKey<ItemGroup> getRegistryKey() {
        return registryKey;
    }

    public ItemGroup getGroup() {
        return group;
    }

    public static void registry() {
        for (ModItems value : values()) {
            Registry.register(Registries.ITEM, value.id, value.item);
            Registry.register(
                    Registries.ITEM_GROUP,
                    value.registryKey,
                    value.group
            );
            ItemGroupEvents.modifyEntriesEvent(value.registryKey).register(c -> {
                ItemStack itemStack = new ItemStack(value.getItem());
                for (int i = value.min_mass_num; i <= value.max_mass_num; i++) {
                    ItemStack copy = itemStack.copy();
                    NbtCompound nbt = new NbtCompound();
                    nbt.putInt("mass_num", i);
                    copy.setNbt(nbt);
                    c.add(copy);
                }
            });
        }
    }
}
