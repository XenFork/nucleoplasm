package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.quickio.minecraft.item.IItemStack;
import union.xenfork.nucleoplasm.api.util.DataList;

@Mixin(ChestBlockEntity.class)
public class ChestBlockEntityMixin extends LockableContainerBlockEntityMixin {
    private DataList<IItemStack> inventory;

    /**
     * @author baka4n
     * @reason rewrite to sql
     */
    @Overwrite
    public int size() {
        return 27;
    }

    public ChestBlockEntityMixin() {
        inventory = new DataList<>("chest_"+((ChestBlockEntity)(Object)this).getPos(), IItemStack.class, size(), new IItemStack(ItemStack.EMPTY));
        inventory.clear();
    }

    @Inject(
            method = "<init>(Lnet/minecraft/block/entity/BlockEntityType;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V",
            at = @At(
                   "HEAD"
            )
    )
    private static void init(BlockEntityType<?> blockEntityType,
                             BlockPos blockPos,
                             BlockState blockState,
                             CallbackInfo ci) {
        new ChestBlockEntityMixin();
    }

    @Inject(method = "<init>(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", at = @At("HEAD"))
    private static void init(BlockPos pos, BlockState state, CallbackInfo ci) {
        new ChestBlockEntityMixin();
    }



    public void readNbt(NbtCompound nbt) {

        super.readNbt(nbt);
        this.inventory = new DataList<>("chest_"+((ChestBlockEntity)(Object)this).getPos(), IItemStack.class, size(), new IItemStack(ItemStack.EMPTY));

        if (!((ChestBlockEntity)(Object)this).deserializeLootTable(nbt)) {
            readNbt(nbt, this.inventory);
        }

    }

    private static void readNbt(NbtCompound nbt, DataList<IItemStack> stacks) {
        NbtList nbtList = nbt.getList("Items", 10);

        for(int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 255;
            if (j < stacks.size()) {
                stacks.set(new IItemStack(ItemStack.fromNbt(nbtCompound), j));
            }
        }

    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!((ChestBlockEntity)(Object)this).serializeLootTable(nbt)) {
            writeNbt(nbt, this.inventory);
        }
    }

    /**
     * @author baka4n
     * @reason get
     */
    @Overwrite
    protected DefaultedList<ItemStack> getInvStackList() {
        DefaultedList<ItemStack> stacks = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        for (int i = 0; i < this.inventory.size(); i++) {
            stacks.set(i, this.inventory.get(i).get());
        }
        return stacks;
    }

    /**
     * @author baka4n
     * @reason set
     */
    @Overwrite
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        for (int i = 0; i < list.size(); i++) {
            this.inventory.set(new IItemStack(list.get(i), i));
        }
    }

    private static NbtCompound writeNbt(NbtCompound nbt, DataList<IItemStack> stacks) {
        return writeNbt(nbt, stacks, true);
    }

    private static NbtCompound writeNbt(NbtCompound nbt, DataList<IItemStack> stacks, boolean setIfEmpty) {
        NbtList nbtList = new NbtList();

        for(int i = 0; i < stacks.size(); ++i) {
            ItemStack itemStack = stacks.get(i).get();
            if (!itemStack.isEmpty()) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte)i);
                itemStack.writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        if (!nbtList.isEmpty() || setIfEmpty) {
            nbt.put("Items", nbtList);
        }

        return nbt;
    }
}
