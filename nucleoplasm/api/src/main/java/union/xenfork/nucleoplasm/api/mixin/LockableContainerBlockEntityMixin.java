package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LockableContainerBlockEntity.class)
public class LockableContainerBlockEntityMixin extends BlockEntityMixin {
    @Shadow private ContainerLock lock;

    @Shadow @Nullable private Text customName;

    /**
     * @author baka4n
     * @reason extends
     */
    @Overwrite
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        lock = ContainerLock.fromNbt(nbt);
        if (nbt.contains("CustomName", 8)) {
            customName = Text.Serializer.fromJson(nbt.getString("CustomName"));
        }

    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.lock.writeNbt(nbt);
        if (this.customName != null) {
            nbt.putString("CustomName", Text.Serializer.toJson(this.customName));
        }

    }

}
