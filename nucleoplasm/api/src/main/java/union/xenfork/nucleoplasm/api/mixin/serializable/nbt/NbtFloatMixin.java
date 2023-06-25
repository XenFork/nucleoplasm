package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtFloat;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtFloat.class)
public class NbtFloatMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 5597647430384994151L;
}
