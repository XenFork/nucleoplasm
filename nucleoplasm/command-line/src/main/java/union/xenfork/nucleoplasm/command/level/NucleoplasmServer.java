package union.xenfork.nucleoplasm.command.level;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

public final class NucleoplasmServer implements DedicatedServerModInitializer {
//    public static final Config config = new Config("nucleoplasm/command/line/config.data");

    @Override
    public void onInitializeServer() {

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {

        });

        ServerPlayerEvents.LOGIN_EVENT.register(serverPlayer -> {

        });

        ServerTickEvents.START_WORLD_TICK.register(world -> {
            for (ServerPlayerEntity player : world.getPlayers()) {

            }
        });

        ServerPlayerEvents.LOGIN_OUT_EVENT.register(entity -> {
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
        });
    }
}
