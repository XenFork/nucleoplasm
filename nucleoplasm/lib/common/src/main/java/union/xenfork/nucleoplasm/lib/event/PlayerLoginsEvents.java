package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class PlayerLoginsEvents {

    public static final Event<ServerPlayerLogin> SERVER_PLAYER_LOGIN_EVENT = EventFactory.createArrayBacked(
            ServerPlayerLogin.class,
            serverPlayerLogins -> player -> {
                for (ServerPlayerLogin serverPlayerLogin : serverPlayerLogins) {
                    serverPlayerLogin.login(player);
                }
            }
    );

    public static final Event<ServerPlayerLoginOut> SERVER_PLAYER_LOGIN_OUT_EVENT = EventFactory.createArrayBacked(
            ServerPlayerLoginOut.class,
            serverPlayerLoginOuts -> player -> {
                for (ServerPlayerLoginOut serverPlayerLoginOut : serverPlayerLoginOuts) {
                    serverPlayerLoginOut.login_out(player);
                }
            }
    );

    @FunctionalInterface
    public interface ServerPlayerLogin {
        void login(ServerPlayerEntity player);
    }
    @FunctionalInterface
    public interface ServerPlayerLoginOut {
        void login_out(ServerPlayerEntity player);
    }
}
