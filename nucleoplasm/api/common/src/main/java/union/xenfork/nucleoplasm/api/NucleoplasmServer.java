package union.xenfork.nucleoplasm.api;

import dev.architectury.event.events.common.*;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;
import union.xenfork.nucleoplasm.api.core.ServerEntityImpl;
import union.xenfork.nucleoplasm.api.event.CommandEvent;
import union.xenfork.nucleoplasm.api.gson.ConfigImpl;

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
        BlockEvent.BREAK.register(impl::blockBreak);
        PlayerEvent.ATTACK_ENTITY.register(impl::attackEntity);
        InteractionEvent.LEFT_CLICK_BLOCK.register(impl::attackBlock);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(impl::interactBlock);
        InteractionEvent.RIGHT_CLICK_ITEM.register(impl::interactItem);
        PlayerEvent.PLAYER_JOIN.register(impl::login);
        PlayerEvent.PLAYER_QUIT.register(impl::logout);
        PlayerEvent.DROP_ITEM.register(impl::dropItem);
        LifecycleEvent.SERVER_STOPPED.register(impl::close);
        LifecycleEvent.SERVER_STOPPED.register(apiImpl::save);
        TickEvent.SERVER_PRE.register(server -> {
            if (server.getTimeReference() % 1200 == 0) {
                impl.save(server);
            }//auto save
        });

        PlayerEvent.PICKUP_ITEM_PRE.register(impl::pickupItem);
        TickEvent.SERVER_LEVEL_PRE.register(world -> {
            impl.tick(world);
            apiImpl.tick(world);
            for (ServerPlayerEntity player : world.getPlayers()) {
                impl.tick(player);
                apiImpl.tick(player);
            }
        });
        BlockEvent.PLACE.register(impl::placeBlock);
        CommandEvent.COMMAND_EVENT.register(impl::execute);

////        UseEntityCallback.EVENT.register(impl::interactEntity);

////        PlayerBlockBreakEvents.BEFORE.register(impl::blockBreak);

//        CommandEvents.execute.register(impl::execute);
    }
}
