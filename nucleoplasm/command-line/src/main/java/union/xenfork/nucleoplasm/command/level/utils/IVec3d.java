package union.xenfork.nucleoplasm.command.level.utils;

import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

import java.io.Serial;
import java.io.Serializable;

public class IVec3d implements Serializable {
    @Serial
    private static final long serialVersionUID = 3409562058355351428L;
    public final double x;
    public final double y;
    public final double z;
    public IVec3d(Vec3d vec3d) {
       this.x = vec3d.x;
       this.y = vec3d.y;
       this.z = vec3d.z;
    }
}
