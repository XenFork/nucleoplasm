package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ServerEntityImpl {
    private final Config config;
    public ServerEntityImpl() {
        config = Config.of(c -> {
            c.name("server");
            c.path(FabricLoader.getInstance().getGameDir().toString());
            c.cache(16L * 1024 * 1024);
        });
        try(DB db = QuickIO.usingDB(config)) {
            Collection<ServerEntity> collection = db.collection(ServerEntity.class);
        }
    }

    public ServerEntity find() {
        ServerEntity serverEntity;
        try(DB db = QuickIO.usingDB(config)) {
            Collection<ServerEntity> collection = db.collection(ServerEntity.class);
            return collection.findAll().get(0);
        }
    }

    public void edit(Consumer<ServerEntity> consumer) {
        ServerEntity serverEntity = find();
        consumer.accept(serverEntity);
        try(DB db = QuickIO.usingDB(config)) {
            Collection<ServerEntity> collection = db.collection(ServerEntity.class);
            collection.save(serverEntity);
        }
    }


}
