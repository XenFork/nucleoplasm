package union.xenfork.nucleoplasm.normandy.login.face;

import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public interface EntityAccessor {
    default boolean getIsLogin() {
        throw new AssertionError();
    }
    default void setIsLogin(boolean isLogin) {}
    default String getPassword() {
        throw new AssertionError();
    }
    default void setPassword(String password) {}
    default double getX() {
        throw new AssertionError();
    }
    default void setX(double x) {}
    default double getY() {
        throw new AssertionError();
    }
    default void setY(double y) {}
    default double getZ() {
        throw new AssertionError();
    }
    default void setZ(double z) {}
    default float getYaw() {throw new AssertionError();}
    default float getPitch() {throw new AssertionError();}
    default void setYaw(float yaw){}

    default void clearKickTime(long kickTime) {}

    default void addKickTime() {}

    default long getKickTime() {throw new AssertionError();}

    default Vec2f getYp() {throw new AssertionError();}

    default void setYp(float p, float y) {}

    default Vec3d getXyz() {throw new AssertionError();}

    default void setXyz(Vec3d xyz) {}

    default void setPitch(float pitch){}
}
