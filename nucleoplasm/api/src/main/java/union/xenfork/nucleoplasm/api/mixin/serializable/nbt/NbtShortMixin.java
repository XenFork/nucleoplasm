package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtShort;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtShort.class)
public class NbtShortMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 5983092295639228880L;
}
