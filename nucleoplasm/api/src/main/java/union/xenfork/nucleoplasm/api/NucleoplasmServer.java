package union.xenfork.nucleoplasm.api;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import union.xenfork.nucleoplasm.api.core.EntityImpl;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    public static final EntityImpl impl = new EntityImpl(FabricLoader.getInstance().getGameDir().resolve("nucleoplasm/data"));
    @Override
    public void onInitializeServer() {
        AttackBlockCallback.EVENT.register(impl::attackBlock);
        AttackEntityCallback.EVENT.register(impl::attackEntity);
        UseEntityCallback.EVENT.register(impl::interactEntity);
        UseItemCallback.EVENT.register(impl::interactItem);
        UseBlockCallback.EVENT.register(impl::interactBlock);
        PlayerBlockBreakEvents.BEFORE.register(impl::blockBreak);
        ServerPlayerEvents.LOGIN_EVENT.register(impl::login);
        ServerPlayerEvents.LOGIN_OUT_EVENT.register(impl::logout);
        ServerLifecycleEvents.SERVER_STOPPED.register(impl::close);
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            for (ServerPlayerEntity player : world.getPlayers()) impl.tick(player);
        });
    }
}
