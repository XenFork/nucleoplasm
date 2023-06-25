package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtInt;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtInt.class)
public class NbtIntMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1173132779995412760L;
}
