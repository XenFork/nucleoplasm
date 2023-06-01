package union.xenfork.nucleoplasm.api.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.quickio.minecraft.item.IItemStack;
import union.xenfork.nucleoplasm.api.util.DataList;

import java.util.List;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow @Final public PlayerEntity player;

    @Shadow public int selectedSlot;
    public final DataList<IItemStack> main;
    public final DataList<IItemStack> armor;
    public final DataList<IItemStack> offHand;
    private final List<DataList<IItemStack>> combinedInventory;

    PlayerInventoryMixin() {
        main = new DataList<>("player_inventory_main_"+player.getEntityName(), IItemStack.class, 36, new IItemStack(ItemStack.EMPTY));
        armor = new DataList<>("player_inventory_armor_"+player.getEntityName(), IItemStack.class, 4, new IItemStack(ItemStack.EMPTY));
        offHand = new DataList<>("player_inventory_offHand_"+player.getEntityName(), IItemStack.class, 1, new IItemStack(ItemStack.EMPTY));
        combinedInventory = ImmutableList.of(main, armor, offHand);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private static void init(PlayerEntity player, CallbackInfo ci) {
        new PlayerInventoryMixin();
    }

    @SuppressWarnings("unused")
    public List<DataList<IItemStack>> getCombinedInventory() {
        return combinedInventory;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public ItemStack getMainHandStack() {
        if (isValidHotbarIndex(selectedSlot)) {
            return main.get(selectedSlot).get();
        } else {
            return ItemStack.EMPTY;
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int getEmptySlot() {
        for(int i = 0; i < main.size(); ++i) {
            if (main.get(i).get().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int getSlotWithStack(ItemStack stack) {
        for(int i = 0; i < this.main.size(); ++i) {
            if (!(this.main.get(i).get()).isEmpty() && ItemStack.canCombine(stack, this.main.get(i).get())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int indexOf(ItemStack stack) {
        for(int i = 0; i < this.main.size(); ++i) {
            ItemStack itemStack = this.main.get(i).get();
            if (!(this.main.get(i).get()).isEmpty() && ItemStack.canCombine(stack, this.main.get(i).get()) && !(this.main.get(i).get()).isDamaged() && !itemStack.hasEnchantments() && !itemStack.hasCustomName()) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int getSwappableHotbarSlot() {
        int i;
        int j;
        for(i = 0; i < 9; ++i) {
            j = (this.selectedSlot + i) % 9;
            if ((this.main.get(j).get()).isEmpty()) {
                return j;
            }
        }

        for(i = 0; i < 9; ++i) {
            j = (this.selectedSlot + i) % 9;
            if (!(this.main.get(j).get()).hasEnchantments()) {
                return j;
            }
        }

        return this.selectedSlot;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void swapSlotWithHotbar(int slot) {
        this.selectedSlot = this.getSwappableHotbarSlot();
        ItemStack itemStack = this.main.get(this.selectedSlot).get();
        this.main.set(this.main.get(slot));
        this.main.set(new IItemStack(itemStack, slot));
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void addPickBlock(ItemStack stack) {
        int i = this.getSlotWithStack(stack);
        if (isValidHotbarIndex(i)) {
            this.selectedSlot = i;
        } else {
            if (i == -1) {
                this.selectedSlot = this.getSwappableHotbarSlot();
                if (!(this.main.get(this.selectedSlot).get()).isEmpty()) {
                    int j = this.getEmptySlot();
                    if (j != -1) {
                        this.main.set(this.main.get(this.selectedSlot));
                    }
                }

                this.main.set(new IItemStack(stack, this.selectedSlot));
            } else {
                this.swapSlotWithHotbar(i);
            }

        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public static boolean isValidHotbarIndex(int slot) {
        return slot >= 0 && slot < 9;
    }

    /**
     * @author baka4n
     * @reason to get canStackAddMore
     */
    @Overwrite
    private boolean canStackAddMore(ItemStack existingStack, ItemStack stack) {
        return !existingStack.isEmpty() && ItemStack.canCombine(existingStack, stack) && existingStack.isStackable() && existingStack.getCount() < existingStack.getMaxCount() && existingStack.getCount() < ((PlayerInventory)(Object)this).getMaxCountPerStack();
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int getOccupiedSlotWithRoomForStack(ItemStack stack) {
        if (canStackAddMore(((PlayerInventory)(Object)this).getStack(this.selectedSlot), stack)) {
            return this.selectedSlot;
        } else if (this.canStackAddMore(((PlayerInventory)(Object)this).getStack(40), stack)) {
            return 40;
        } else {
            for(int i = 0; i < this.main.size(); ++i) {
                if (this.canStackAddMore(this.main.get(i).get(), stack)) {
                    return i;
                }
            }

            return -1;
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void setStack(int slot, ItemStack stack) {
        DataList<IItemStack> dataList = null;

        DataList<IItemStack> defaultedList2;
        for(var var4 = this.combinedInventory.iterator(); var4.hasNext(); slot -= defaultedList2.size()) {
            defaultedList2 = var4.next();
            if (slot < defaultedList2.size()) {
                dataList = defaultedList2;
                break;
            }
        }

        if (dataList != null) {
            dataList.set(new IItemStack(stack, slot));
        }

    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @SuppressWarnings("DataFlowIssue")
    @Overwrite
    private int addStack(int slot, ItemStack stack) {
        Item item = stack.getItem();
        int i = stack.getCount();
        ItemStack itemStack = ((PlayerInventory)(Object)this).getStack(slot);
        if (itemStack.isEmpty()) {
            itemStack = new ItemStack(item, 0);
            if (stack.hasNbt()) {
                itemStack.setNbt(stack.getNbt().copy());
            }

            this.setStack(slot, itemStack);
        }

        int j = Math.min(i, itemStack.getMaxCount() - itemStack.getCount());

        if (j > ((PlayerInventory)(Object)this).getMaxCountPerStack() - itemStack.getCount()) {
            j = ((PlayerInventory)(Object)this).getMaxCountPerStack() - itemStack.getCount();
        }

        if (j != 0) {
            i -= j;
            itemStack.increment(j);
            itemStack.setBobbingAnimationTime(5);
        }
        return i;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void updateItems() {

        for (DataList<IItemStack> defaultedList : this.combinedInventory) {
            for (int i = 0; i < defaultedList.size(); ++i) {
                if (!(defaultedList.get(i).get()).isEmpty()) {
                    (defaultedList.get(i).get()).inventoryTick(this.player.getWorld(), this.player, i, this.selectedSlot == i);
                }
            }
        }

    }

    /**
     * @author baka4n
     * @reason using
     */
    @Overwrite
    private int addStack(ItemStack stack) {
        int i = this.getOccupiedSlotWithRoomForStack(stack);
        if (i == -1) {
            i = this.getEmptySlot();
        }

        return i == -1 ? stack.getCount() : this.addStack(i, stack);
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public boolean insertStack(int slot, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        } else {
            try {
                if (stack.isDamaged()) {
                    if (slot == -1) {
                        slot = this.getEmptySlot();
                    }

                    if (slot >= 0) {
                        this.main.set(new IItemStack(stack.copyAndEmpty(), slot));
                        this.main.get(slot).get().setBobbingAnimationTime(5);
                        return true;
                    } else if (this.player.getAbilities().creativeMode) {
                        stack.setCount(0);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    int i;
                    do {
                        i = stack.getCount();
                        if (slot == -1) {
                            stack.setCount(this.addStack(stack));
                        } else {
                            stack.setCount(this.addStack(slot, stack));
                        }
                    } while(!stack.isEmpty() && stack.getCount() < i);

                    if (stack.getCount() == i && this.player.getAbilities().creativeMode) {
                        stack.setCount(0);
                        return true;
                    } else {
                        return stack.getCount() < i;
                    }
                }
            } catch (Throwable var6) {
                CrashReport crashReport = CrashReport.create(var6, "Adding item to inventory");
                CrashReportSection crashReportSection = crashReport.addElement("Item being added");
                crashReportSection.add("Item ID", Item.getRawId(stack.getItem()));
                crashReportSection.add("Item data", stack.getDamage());
                crashReportSection.add("Item name", () -> {
                    return stack.getName().getString();
                });
                throw new CrashException(crashReport);
            }
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public ItemStack removeStack(int slot, int amount) {
        DataList<IItemStack> list = null;

        DataList<IItemStack> dataList;
        for(var var4 = this.combinedInventory.iterator(); var4.hasNext(); slot -= dataList.size()) {
            dataList = var4.next();
            if (slot < dataList.size()) {
                list = dataList;
                break;
            }
        }

        if (list != null && !list.get(slot).get().isEmpty()) {
            return splitStack(list, slot, amount);
        } else {
            return ItemStack.EMPTY;
        }
    }

    private static ItemStack splitStack(DataList<IItemStack> stacks, int slot, int amount) {
        return slot >= 0 && slot < stacks.size() && !stacks.get(slot).get().isEmpty() && amount > 0 ? stacks.get(slot).get().split(amount) : ItemStack.EMPTY;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void removeOne(ItemStack stack) {
        for (DataList<IItemStack> dataList : this.combinedInventory) {
            for (int i = 0; i < dataList.size(); ++i) {
                if (dataList.get(i).get() == stack) {
                    dataList.set(new IItemStack(ItemStack.EMPTY, i));
                    break;
                }
            }
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public ItemStack removeStack(int slot) {
        DataList<IItemStack> dataList = null;

        DataList<IItemStack> dataList2;
        for(var var3 = this.combinedInventory.iterator(); var3.hasNext(); slot -= dataList2.size()) {
            dataList2 = var3.next();
            if (slot < dataList2.size()) {
                dataList = dataList2;
                break;
            }
        }

        if (dataList != null && !dataList.get(slot).get().isEmpty()) {
            ItemStack itemStack = dataList.get(slot).get();
            dataList.set(new IItemStack(ItemStack.EMPTY, slot));
            return itemStack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public float getBlockBreakingSpeed(BlockState block) {
        return this.main.get(this.selectedSlot).get().getMiningSpeedMultiplier(block);
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public NbtList writeNbt(NbtList nbtList) {
        int i;
        NbtCompound nbtCompound;
        for(i = 0; i < this.main.size(); ++i) {
            if (!this.main.get(i).get().isEmpty()) {
                nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte)i);
                this.main.get(i).get().writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        for(i = 0; i < this.armor.size(); ++i) {
            if (!this.armor.get(i).get().isEmpty()) {
                nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte)(i + 100));
                this.armor.get(i).get().writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        for(i = 0; i < this.offHand.size(); ++i) {
            if (!this.offHand.get(i).get().isEmpty()) {
                nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte)(i + 150));
                this.offHand.get(i).get().writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        return nbtList;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void readNbt(NbtList nbtList) {
        this.main.clear();
        this.armor.clear();
        this.offHand.clear();

        for(int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 255;
            ItemStack itemStack = ItemStack.fromNbt(nbtCompound);
            if (!itemStack.isEmpty()) {
                if (j < this.main.size()) {
                    this.main.set(new IItemStack(itemStack, j));
                } else if (j >= 100 && j < this.armor.size() + 100) {
                    this.armor.set(new IItemStack(itemStack, j - 100));
                } else if (j >= 150 && j < this.offHand.size() + 150) {
                    this.offHand.set(new IItemStack(itemStack, j - 150));
                }
            }
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int size() {
        return this.main.size() + this.armor.size() + this.offHand.size();
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public boolean isEmpty() {
        var var1 = this.main.iterator();
        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                var1 = this.armor.iterator();
                do {
                    if (!var1.hasNext()) {
                        var1 = this.offHand.iterator();

                        do {
                            if (!var1.hasNext()) {
                                return true;
                            }

                            itemStack = var1.next().get();
                        } while(itemStack.isEmpty());

                        return false;
                    }
                    itemStack = var1.next().get();
                } while(itemStack.isEmpty());
                return false;
            }
            itemStack = var1.next().get();
        } while(itemStack.isEmpty());
        return false;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public ItemStack getStack(int slot) {
        DataList<IItemStack> list = null;

        DataList<IItemStack> defaultedList;
        for(var var3 = this.combinedInventory.iterator(); var3.hasNext(); slot -= defaultedList.size()) {
            defaultedList = var3.next();
            if (slot < defaultedList.size()) {
                list = defaultedList;
                break;
            }
        }

        if (list == null) {
            return ItemStack.EMPTY;
        } else {
            return list.get(slot).get();
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public ItemStack getArmorStack(int slot) {
        return this.armor.get(slot).get();
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @Overwrite
    public void damageArmor(DamageSource damageSource, float amount, int[] slots) {
        if (!(amount <= 0.0F)) {
            amount /= 4.0F;
            if (amount < 1.0F) {
                amount = 1.0F;
            }

            int[] var4 = slots;
            int var5 = slots.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                int i = var4[var6];
                ItemStack itemStack = this.armor.get(i).get();
                if ((!damageSource.isIn(DamageTypeTags.IS_FIRE) || !itemStack.getItem().isFireproof()) && itemStack.getItem() instanceof ArmorItem) {
                    itemStack.damage((int)amount, this.player, (player) -> {
                        player.sendEquipmentBreakStatus(EquipmentSlot.fromTypeIndex(EquipmentSlot.Type.ARMOR, i));
                    });
                }
            }

        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void dropAll() {
        for (DataList<IItemStack> list : this.combinedInventory) {
            for (int i = 0; i < list.size(); ++i) {
                ItemStack itemStack = (ItemStack) list.get(i).get();
                if (!itemStack.isEmpty()) {
                    this.player.dropItem(itemStack, true, false);
                    list.set(new IItemStack(ItemStack.EMPTY, i));
                }
            }
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public boolean contains(ItemStack stack) {

        for (DataList<IItemStack> list : this.combinedInventory) {
            for (IItemStack iItemStack : list) {
                ItemStack itemStack = iItemStack.get();
                if (!itemStack.isEmpty() && ItemStack.canCombine(itemStack, stack)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public boolean contains(TagKey<Item> tag) {

        for (DataList<IItemStack> list : this.combinedInventory) {
            for (IItemStack iItemStack : list) {
                ItemStack itemStack = iItemStack.get();
                if (!itemStack.isEmpty() && itemStack.isIn(tag)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void clear() {
        for (DataList<IItemStack> iItemStacks : this.combinedInventory) {
            iItemStacks.clear();
        }
    }

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public void populateRecipeFinder(RecipeMatcher finder) {
        for (IItemStack iItemStack : this.main) {
            ItemStack itemStack = iItemStack.get();
            finder.addUnenchantedInput(itemStack);
        }
    }

}
