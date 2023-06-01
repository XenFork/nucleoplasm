package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void readNbt(NbtCompound nbt) {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected void writeNbt(NbtCompound nbt) {
    }
}
