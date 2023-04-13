package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ServerPlayerEvents {
    public static final Event<ServerPlayerLogin> LOGIN_EVENT
            = EventFactory.createArrayBacked(ServerPlayerLogin.class,
            callbacks -> player -> {
                for (ServerPlayerLogin callback : callbacks) {
                    callback.login(player);
                }
            });
    public static final Event<ServerPlayerLoginOut> LOGIN_OUT_EVENT
            = EventFactory.createArrayBacked(ServerPlayerLoginOut.class,
            callbacks -> player -> {
                for (ServerPlayerLoginOut callback : callbacks) {
                    callback.login_out(player);
                }
            });
    public static final Event<ServerPlayerTick> PLAYER_TICK_EVENT
            = EventFactory.createArrayBacked(ServerPlayerTick.class,
            callbacks -> player -> {
                for (ServerPlayerTick callback : callbacks) {
                    callback.tick(player);
                }
            });
    public interface ServerPlayerLogin {
        void login(ServerPlayerEntity player);
    }
    public interface ServerPlayerLoginOut {
        void login_out(ServerPlayerEntity player);
    }
    public interface ServerPlayerTick {
        void tick(ServerPlayerEntity entity);
    }
}
