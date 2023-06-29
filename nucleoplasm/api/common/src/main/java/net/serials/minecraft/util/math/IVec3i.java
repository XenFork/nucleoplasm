package net.serials.minecraft.util.math;

import net.minecraft.util.math.Vec3i;

import java.io.Serial;
import java.io.Serializable;

public class IVec3i implements Serializable {

    @Serial
    private static final long serialVersionUID = 1081824172893117406L;

    public int x,y,z;
    public IVec3i(Vec3i vec3i) {
        x = vec3i.getX();
        y = vec3i.getY();
        z = vec3i.getZ();
    }
    public IVec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3i get() {
        return new Vec3i(x,y,z);
    }
}
