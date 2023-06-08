package union.xenfork.nucleoplasm.api.sql;

import com.google.gson.annotations.SerializedName;

public class NucleoplasmEntity {
    @SerializedName("UUID")
    public String uuid;
    @SerializedName("x")
    public double x;
    @SerializedName("y")
    public double y;
    @SerializedName("z")
    public double z;
    @SerializedName("first join time")
    public long first_join_time;//首次加入服务器时间
    @SerializedName("login time")
    public long login_time;//上个登录时间
    @SerializedName("last logout time")
    public long last_logout_time;//上个登出时间
    @SerializedName("is login")
    public boolean is_login;//是否已经登录
    @SerializedName("gamemode")
    public String gamemode;//游戏模式
    @SerializedName("fly")
    public boolean fly;//是否飞行
    @SerializedName("is invincible")
    public boolean is_invincible;//是否为无敌状态

    @SerializedName("is invisible")
    public boolean is_invisible;//是否隐身

    public static NucleoplasmEntity of() {
        return new NucleoplasmEntity();
    }
}
