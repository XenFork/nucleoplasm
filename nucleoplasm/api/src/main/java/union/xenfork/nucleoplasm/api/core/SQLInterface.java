package union.xenfork.nucleoplasm.api.core;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface SQLInterface {
    void save();
    void close();
    void create(ServerPlayerEntity entity);
    //玩家登录
    void login(ServerPlayerEntity entity);
    //玩家登出
    void logout(ServerPlayerEntity entity);
    //滴答
    void tick(ServerPlayerEntity entity);
    //攻击方块
    void attackBlock(PlayerEntity entity);
    //攻击实体
    void attackEntity(PlayerEntity entity);
    //使用实体
    void interactEntity(PlayerEntity entity);
    void interactItem(PlayerEntity entity);
    void interactBlock(PlayerEntity entity);
    void blockBreak(PlayerEntity entity);
    void pickupItem(PlayerEntity entity);
}
