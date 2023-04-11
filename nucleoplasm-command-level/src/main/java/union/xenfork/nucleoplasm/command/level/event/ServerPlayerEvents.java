package union.xenfork.nucleoplasm.command.level.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public final class ServerPlayerEvents {
    public static final Event<ServerPlayerLogin> LOGIN_EVENT
            = EventFactory.createArrayBacked(ServerPlayerLogin.class,
            callbacks -> player -> {
                for (ServerPlayerLogin callback : callbacks) {
                    callback.login(player);
                }
            });
    public interface ServerPlayerLogin {
        void login(ServerPlayerEntity player);
    }
}
