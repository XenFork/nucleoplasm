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
                stacks.set(j, new IItemStack(ItemStack.fromNbt(nbtCompound)));
            }
        }

    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }

    }
}
