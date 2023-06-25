package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtIntArray;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtIntArray.class)
public class NbtIntArrayMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 6984034969021081357L;
}
