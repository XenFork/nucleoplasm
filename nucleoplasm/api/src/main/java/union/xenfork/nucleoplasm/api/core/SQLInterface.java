package union.xenfork.nucleoplasm.api.core;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface SQLInterface {
    void save();
    void close();
    void create(ServerPlayerEntity entity);
    void login(ServerPlayerEntity entity);
    void logout(ServerPlayerEntity entity);
}
