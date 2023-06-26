package union.xenfork.nucleoplasm.normandy.login.mixin;

import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.serials.minecraft.util.math.IVec2f;
import net.serials.minecraft.util.math.IVec3d;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;

@Debug(export = true)
@Mixin(Entity.class)
public class MixinEntity implements EntityAccessor {
    public boolean is_login;
    public String password;
    public IVec3d xyz;
    public IVec2f py;
    public long kickTime;

    @Override
    public void clearKickTime(long kickTime) {
        this.kickTime = 0L;
    }
    @Override
    public void addKickTime() {
        this.kickTime++;
    }

    @Override
    public long getKickTime() {
        return kickTime;
    }

    @Override
    public Vec2f getYp() {
        return py.get();
    }
    @Override
    public void setYp(float p, float y) {
        this.py = new IVec2f(p, y);
    }

    @Override
    public Vec3d getXyz() {
        return xyz.get();
    }
    @Override
    public void setXyz(Vec3d xyz) {
        this.xyz = new IVec3d(xyz);
    }

    @Override
    public void setPitch(float pitch) {
        py.x = pitch;
    }

    @Override
    public void setYaw(float yaw) {
        py.y = yaw;
    }

    @Override
    public float getYaw() {
        return py.y;
    }

    @Override
    public float getPitch() {
        return py.x;
    }

    @Override
    public boolean getIsLogin() {
        return is_login;
    }

    @Override
    public void setIsLogin(boolean isLogin) {
        is_login = isLogin;
    }

    @Override
    public double getX() {
        return xyz.x;
    }

    @Override
    public void setX(double x) {
        xyz.x = x;
    }

    @Override
    public double getY() {
        return xyz.y;
    }

    @Override
    public void setY(double y) {
        xyz.y = y;
    }

    @Override
    public double getZ() {
        return xyz.z;
    }

    @Override
    public void setZ(double z) {
        xyz.z = z;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
