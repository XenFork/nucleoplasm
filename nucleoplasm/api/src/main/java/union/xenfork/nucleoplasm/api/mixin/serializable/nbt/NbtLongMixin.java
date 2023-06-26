package union.xenfork.nucleoplasm.api.mixin.serializable.nbt;

import net.minecraft.nbt.NbtLong;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;

@Debug(export = true)
@Mixin(NbtLong.class)
public class NbtLongMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 7019221243661487785L;//序列化Vec3d的测试
}
