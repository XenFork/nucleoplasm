package union.xenfork.nucleoplasm.api.mixin.serializable.util.math;

import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;
@Debug(export = true)
@Mixin(Vec3d.class)
public class Vec3dMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = 111850023630830806L;//序列化Vec3d的测试
}
