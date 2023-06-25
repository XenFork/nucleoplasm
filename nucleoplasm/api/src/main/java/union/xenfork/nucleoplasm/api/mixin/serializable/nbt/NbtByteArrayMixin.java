package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtByteArray;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;
@Debug(export = true)
@Mixin(NbtByteArray.class)
public class NbtByteArrayMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 3050252183543564249L;
}
