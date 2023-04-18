package union.xenfork.nucleoplasm.api.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;
import union.xenfork.nucleoplasm.api.quickio.utils.PlayerDB;

import static union.xenfork.nucleoplasm.api.NucleoplasmApi.logger;

public class NucleoplasmApiServer implements DedicatedServerModInitializer {
    public static PlayerDB<PlayerEntity> playerDB;
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            playerDB = new PlayerDB<>("player", PlayerEntity.class);
        });
        ServerPlayerEvents.LOGIN_EVENT.register(player -> {
            PlayerEntity playerEntity = playerDB.get(player);
            if (playerEntity == null) {
                playerDB.add(player);
                logger.info("%s is first join %s server!".formatted(player, player.server.getServerMotd()));
            } else {
                logger.info("%s is join %s server!".formatted(player, player.server.getServerMotd()));
            }
        });

        ServerPlayerEvents.LOGIN_OUT_EVENT.register(player -> {
            PlayerEntity playerEntity = playerDB.get(player);
            if (playerEntity == null) {
                logger.info("%s is don't add this database".formatted(player));
            } else {
                logger.info("%s is quit %s server!".formatted(player, player.server.getServerMotd()));
            }
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            playerDB.close();
        });
    }
}
