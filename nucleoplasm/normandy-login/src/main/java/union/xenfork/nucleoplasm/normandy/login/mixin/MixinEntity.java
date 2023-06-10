package union.xenfork.nucleoplasm.normandy.login.mixin;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;

@Debug(export = true)
@Mixin(Entity.class)
public class MixinEntity implements EntityAccessor {
    public boolean is_login;
    public String password;
    public double x,y,z;

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
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
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
