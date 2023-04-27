package union.xenfork.nucleoplasm_normandy_login.quickio.nnl;

import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;

public class NNLPlayerEntity extends PlayerEntity {
    public boolean isLogin;
    public long first_join_time,Last_join_time;
    public float health;
    public NNLPlayerEntity() {
        super();
    }
    public NNLPlayerEntity(PlayerEntity entity) {
        player_name = entity.player_name;
        uuid = entity.uuid;
        x = entity.x;
        y = entity.y;
        z = entity.z;
    }
}