package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtDouble;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtDouble.class)
public class NbtDoubleMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = -5460974239513072759L;
}
