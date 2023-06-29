package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import union.xenfork.nucleoplasm.api.ApiExpectPlatform;

import java.util.function.Consumer;

public class ServerEntityImpl {
    private final Config config;
    private ServerEntity serverEntity;
    public ServerEntityImpl() {
        config = Config.of(c -> {
            c.name("server");
            c.path(ApiExpectPlatform.getGameDir().toString());
            c.cache(16L * 1024 * 1024);
        });
        try(DB db = QuickIO.usingDB(config)) {
            Collection<ServerEntity> collection = db.collection(ServerEntity.class);
        }
    }

    public ServerEntity find() {
        try(DB db = QuickIO.usingDB(config)) {
            Collection<ServerEntity> collection = db.collection(ServerEntity.class);
            serverEntity = collection.findAll().get(0);
            return serverEntity;
        }
    }

    public ServerEntity getServerEntity() {
        return serverEntity;
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
