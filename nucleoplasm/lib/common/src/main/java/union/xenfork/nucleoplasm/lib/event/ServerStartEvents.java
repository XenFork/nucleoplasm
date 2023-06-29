package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.server.MinecraftServer;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

/**
 * @author FabricMC
 */
public class ServerStartEvents {

    public static final Event<ServerStarting> SERVER_START_EVENT = EventFactory.createArrayBacked(
            ServerStarting.class,
            serverStarts -> server -> {
                for (ServerStarting serverStart : serverStarts)
                    serverStart.onServerStarting(server);
            }
    );

    public static final Event<ServerStarted> SERVER_STARTED_EVENT = EventFactory.createArrayBacked(
            ServerStarted.class,
            serverStarteds -> server -> {
                for (ServerStarted serverStarted : serverStarteds)
                    serverStarted.onServerStarted(server);
            }
    );
    @FunctionalInterface
    public interface ServerStarting {
        void onServerStarting(MinecraftServer server);
    }
    @FunctionalInterface
    public interface ServerStarted {
        void onServerStarted(MinecraftServer server);
    }





}
