package union.xenfork.nucleoplasm.api;

import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;
import union.xenfork.nucleoplasm.api.core.ServerEntityImpl;
import union.xenfork.nucleoplasm.api.gson.ConfigImpl;
import union.xenfork.nucleoplasm.lib.event.*;

public class NucleoplasmServer {
    public static final EntityImpl impl;
    public static final ServerEntityImpl serverImpl;
    public static final ConfigImpl apiImpl;

    static {
        impl = new EntityImpl(ApiExpectPlatform.getGameDir().resolve("nucleoplasm/data"));
        serverImpl = new ServerEntityImpl();
        apiImpl = new ConfigImpl(ApiExpectPlatform.getConfigDir().resolve("nucleoplasm/api"), "config");
    }

    public static void server() {
        BlockEvents.ATTACK_BLOCK_EVENT.register(impl::attackBlock);
//        AttackEntityCallback.EVENT.register(impl::attackEntity);
//        UseEntityCallback.EVENT.register(impl::interactEntity);
//        UseItemCallback.EVENT.register(impl::interactItem);
//        UseBlockCallback.EVENT.register(impl::interactBlock);
//        PlayerBlockBreakEvents.BEFORE.register(impl::blockBreak);
        PlayerLoginsEvents.SERVER_PLAYER_LOGIN_EVENT.register(impl::login);
        PlayerLoginsEvents.SERVER_PLAYER_LOGIN_OUT_EVENT.register(impl::logout);
        ItemEvents.DROP_ITEM_EVENT.register(impl::dropItem);
        ServerStopEvents.SERVER_STOPPED_EVENT.register(impl::close);
        ServerStopEvents.SERVER_STOPPED_EVENT.register(apiImpl::save);
        ServerTickEvents.SERVER_TICK_EVENT.register(server -> {
            if (server.getTimeReference() % 1200 == 0) {
                impl.save(server);
            }//auto save
        });
        ItemEvents.PICK_ITEM_EVENT.register(impl::pickupItem);
        ServerWorldTickEvents.WORLD_TICK_EVENT.register(world -> {
            impl.tick(world);
            apiImpl.tick(world);
            for (ServerPlayerEntity player : world.getPlayers()) {
                impl.tick(player);
                apiImpl.tick(player);
            }
        });


        CommandEvents.execute.register(impl::execute);
    }
}
