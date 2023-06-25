package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtLongArray;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtLongArray.class)
public class NbtLongArrayMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1159362168182492799L;
}
