package union.xenfork.nucleoplasm.command.level.utils;

import net.minecraft.util.math.Vec2f;

import java.io.Serial;
import java.io.Serializable;

public class IVec2f implements Serializable {
    @Serial
    private static final long serialVersionUID = 3068882391642377767L;
    public final float x;
    public final float y;
    public IVec2f(Vec2f vec2f) {
       this.x = vec2f.x;
       this.y = vec2f.y;
    }

    public IVec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
