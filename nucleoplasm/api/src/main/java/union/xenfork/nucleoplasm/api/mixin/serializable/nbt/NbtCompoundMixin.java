package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtCompound.class)
public class NbtCompoundMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = -6652179279718398446L;
}
