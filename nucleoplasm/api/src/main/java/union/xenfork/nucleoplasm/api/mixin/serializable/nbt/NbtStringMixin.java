package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtString;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtString.class)
public abstract class NbtStringMixin implements Serializable {

    @Serial
    private static final long serialVersionUID = -4692153822315647693L;//序列化Vec3d的测试
}
