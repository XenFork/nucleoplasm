package union.xenfork.nucleoplasm.normandy.login.face;

import union.xenfork.nucleoplasm.api.core.Entity;

import java.util.ArrayList;

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
    default ArrayList<String> getIps() {throw new AssertionError();}
    default void addIp(String ip) {}
    default float getYaw() {throw new AssertionError();}
    default float getPitch() {throw new AssertionError();}
    default void setYaw(float yaw){}
    default void setPitch(float pitch){}
}
