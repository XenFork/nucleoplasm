package union.xenfork.nucleoplasm.lib.event.lifecycle;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class LoadServerWorldEvents {
    public static final Event<Load> LOAD = EventFactory.createArrayBacked(Load.class, callbacks -> (server, world) -> {
        for (Load callback : callbacks) {
            callback.onWorldLoad(server, world);
        }
    });
    public static final Event<Unload> UNLOAD = EventFactory.createArrayBacked(Unload.class, callbacks -> (server, world) -> {
        for (Unload callback : callbacks) {
            callback.onWorldUnload(server, world);
        }
    });

    @FunctionalInterface
    public interface Load {
        void onWorldLoad(MinecraftServer server, ServerWorld world);
    }

    @FunctionalInterface
    public interface Unload {
        void onWorldUnload(MinecraftServer server, ServerWorld world);
    }

    private LoadServerWorldEvents() {
    }
}
