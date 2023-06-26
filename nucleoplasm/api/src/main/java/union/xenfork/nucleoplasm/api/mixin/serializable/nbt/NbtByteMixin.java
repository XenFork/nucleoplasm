package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtByte;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtByte.class)
public class NbtByteMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = -4633201556303539150L;
}
