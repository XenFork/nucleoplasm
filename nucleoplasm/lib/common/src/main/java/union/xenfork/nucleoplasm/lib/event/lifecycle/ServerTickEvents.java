package union.xenfork.nucleoplasm.lib.event.lifecycle;

import net.minecraft.server.MinecraftServer;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class ServerTickEvents {
    public static final Event<ServerTick> SERVER_TICK_EVENT = EventFactory.createArrayBacked(
            ServerTick.class,
            serverTicks -> server -> {
                for (ServerTick serverTick : serverTicks)
                    serverTick.onServerTick(server);
            }
    );

    public static final Event<EndServerTick> END_SERVER_TICK_EVENT = EventFactory.createArrayBacked(
            EndServerTick.class,
            endServerTicks -> server -> {
                for (EndServerTick endServerTick : endServerTicks)
                    endServerTick.onEndServerTick(server);
            }
    );
    @FunctionalInterface
    public interface ServerTick {
        void onServerTick(MinecraftServer server);
    }
    @FunctionalInterface
    public interface EndServerTick {
        void onEndServerTick(MinecraftServer server);
    }
}
