package union.xenfork.nucleoplasm.api.quickio;

import com.github.artbits.quickio.core.IOEntity;

import java.util.ArrayList;
import java.util.function.Consumer;

public class PlayerEntity extends IOEntity {
    public String player_name;
    public ArrayList<String> groups;//权限组名
    public boolean isLogin;//判断是否已经登陆
    public long join_time, login_time;//加入游戏的时间， 登陆的计时器

    public String password, email;//密码，邮箱

    public static PlayerEntity of(Consumer<PlayerEntity> consumer) {
        PlayerEntity playerEntity = new PlayerEntity();
        consumer.accept(playerEntity);
        return playerEntity;
    }
}
