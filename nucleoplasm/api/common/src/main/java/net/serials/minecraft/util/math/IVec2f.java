package net.serials.minecraft.util.math;

import net.minecraft.util.math.Vec2f;

import java.io.Serial;
import java.io.Serializable;

public class IVec2f implements Serializable {

    @Serial
    private static final long serialVersionUID = 1081824172893117406L;

    public float x, y;
    public IVec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public IVec2f(Vec2f vec2f) {
        this.x = vec2f.x;
        this.y = vec2f.y;
    }

    public Vec2f get() {
        return new Vec2f(x, y);
    }
}
