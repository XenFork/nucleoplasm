package union.xenfork.nucleoplasm.api;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import union.xenfork.nucleoplasm.api.core.*;
import union.xenfork.nucleoplasm.api.event.CommandEvents;
import union.xenfork.nucleoplasm.api.event.ItemEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.api.gson.ConfigImpl;

public class NucleoplasmServer implements DedicatedServerModInitializer {
//    public static final EntityImpl impl = new EntityImpl(FabricLoader.getInstance().getGameDir().resolve("nucleoplasm/data"));
    public static final EntityImpl impl = new EntityImpl(FabricLoader.getInstance().getGameDir().resolve("nucleoplasm/data"));
    public static final TEntityImpl<ServerEntity> serverImpl = new TEntityImpl<>("server", FabricLoader.getInstance().getGameDir(), ServerEntity.class);
    public static final TEntityImpl<GlobalEntity> globalImpl = new TEntityImpl<>("global", System.getProperty("user.home"), GlobalEntity.class);
    public static final ConfigImpl apiImpl = new ConfigImpl(FabricLoader.getInstance().getConfigDir().resolve("nucleoplasm/api"), "config");
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
        ServerPlayerEvents.DROP_ITEM_EVENT.register(impl::dropItem);
        ServerLifecycleEvents.SERVER_STOPPED.register(impl::close);
        ServerLifecycleEvents.SERVER_STOPPED.register(apiImpl::save);
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            impl.tick(world);
            apiImpl.tick(world);
            for (ServerPlayerEntity player : world.getPlayers()) {
                impl.tick(player);
                apiImpl.tick(player);
            }
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(impl::save);//close save
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            if (server.getTimeReference() % 1200 == 0) {
                impl.save(server);
            }//auto save
        });
        ItemEvents.PICK_ITEM_EVENT.register(impl::pickupItem);
        CommandEvents.execute.register(impl::execute);

    }
}
