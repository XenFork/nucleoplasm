package union.xenfork.nucleoplasm.lib.event;

import net.minecraft.server.world.ServerWorld;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class ServerWorldTickEvents {

    public static final Event<WorldTick> WORLD_TICK_EVENT = EventFactory.createArrayBacked(
            WorldTick.class,
            worldTicks -> world -> {
                for (WorldTick worldTick : worldTicks) {
                    worldTick.tick(world);
                }
            }
    );

    public static final Event<EndWorldTick> END_WORLD_TICK_EVENT = EventFactory.createArrayBacked(
            EndWorldTick.class,
            endWorldTicks -> world -> {
                for (EndWorldTick endWorldTick : endWorldTicks) {
                    endWorldTick.tick(world);
                }
            }
    );
    @FunctionalInterface
    public interface WorldTick {
        void tick(ServerWorld world);
    }
    @FunctionalInterface
    public interface EndWorldTick {
        void tick(ServerWorld world);
    }
}
