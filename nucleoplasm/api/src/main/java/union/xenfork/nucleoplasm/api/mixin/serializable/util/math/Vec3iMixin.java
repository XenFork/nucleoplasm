package union.xenfork.nucleoplasm.api.mixin.serializable.util.math;

import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serial;
import java.io.Serializable;
@Debug(export = true)
@Mixin(Vec3i.class)
public class Vec3iMixin implements Serializable {
    @Serial
    private static final long serialVersionUID = -1796406931002988850L;//序列化Vec3d的测试

}
