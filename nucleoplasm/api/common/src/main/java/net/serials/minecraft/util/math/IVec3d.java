package net.serials.minecraft.util.math;

import net.minecraft.util.math.Vec3d;

import java.io.Serial;
import java.io.Serializable;

public class IVec3d implements Serializable {

    @Serial
    private static final long serialVersionUID = 5429372291435075960L;

    public double x,y,z;

    public IVec3d(Vec3d vec3d) {
        x = vec3d.x;
        y = vec3d.y;
        z = vec3d.z;
    }

    public IVec3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d get() {
        return new Vec3d(x,y,z);
    }
}
