package union.xenfork.nucleoplasm.lib.event.lifecycle;

import net.minecraft.server.MinecraftServer;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class ServerStopEvents {

    public static final Event<ServerStopping> SERVER_STOPPING_EVENT = EventFactory.createArrayBacked(
            ServerStopping.class,
            serverStoppings -> server -> {
                for (ServerStopping serverStopping : serverStoppings)
                    serverStopping.onServerStopping(server);
            }
    );

    public static final Event<ServerStopped> SERVER_STOPPED_EVENT = EventFactory.createArrayBacked(
            ServerStopped.class,
            serverStoppeds -> server -> {
                for (ServerStopped serverStopped : serverStoppeds)
                    serverStopped.onServerStopped(server);
            }
    );

    public interface ServerStopping {
        void onServerStopping(MinecraftServer server);
    }
    public interface ServerStopped {
        void onServerStopped(MinecraftServer server);
    }
}
