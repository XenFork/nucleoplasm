package union.xenfork.nucleoplasm.api.mixin.serializable.util.math;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;
@Debug(export = true)
@Mixin(Vec2f.class)
public class Vec2fMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 7478875020002517804L;//序列化Vec3d的测试
}
