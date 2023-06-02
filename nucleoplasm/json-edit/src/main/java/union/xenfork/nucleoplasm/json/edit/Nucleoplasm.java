package union.xenfork.nucleoplasm.json.edit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

public class Nucleoplasm implements ModInitializer {
    public static final Logger logger = LoggerFactory.getLogger("nucleoplasm_json_edit");
    public static final Path dir = FabricLoader.getInstance().getGameDir().resolve("json");
    public static final Path test_dir = FabricLoader.getInstance().getGameDir().resolve("test_json");
    public static final Path recipe = dir.resolve("recipes");
    public static final Path loot_table = dir.resolve("loot_table");
    public static NJEConfigs config;

    @Override
    public void onInitialize() {
        config = new NJEConfigs("nucleoplasm/njs/config.json");
        try {
            config.create();
        } catch (IOException e) {
            logger.error("fail to create", e);
        }
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            try {
                config.tick();
            } catch (IOException e) {
                logger.error("error load", e);
            }
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            try {
                config.save();
            } catch (IOException e) {
                logger.error("don't save this config");
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                config.save();
            } catch (IOException e) {
                logger.error("don't save this config");
            }
        });
    }
}
