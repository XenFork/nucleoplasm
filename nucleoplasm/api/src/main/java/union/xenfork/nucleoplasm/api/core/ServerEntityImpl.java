package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.server.MinecraftServer;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ServerEntityImpl {
    private final DB db;
    private List<ServerEntity> all;
    public ServerEntityImpl(Path path) {
        Config config = Config.of(c -> {
            c.name("player");
            c.path(path.toFile().getAbsolutePath());
            c.cache(16L * 1024 * 1024);
        });
        db = QuickIO.usingDB(config);
        Collection<ServerEntity> collection = db.collection(ServerEntity.class);
        all = collection.findAll();
    }

    public ServerEntity find(MinecraftServer server) {
        var serverEntityStream = all.stream().filter(serverEntity -> serverEntity.threadName.equals(server.getName())).toList();
        if (serverEntityStream.isEmpty()) {
            ServerEntity of = ServerEntity.of(serverEntity -> {
                serverEntity.threadName = server.getName();
            });
            all.add(of);
            return of;
        }
        return serverEntityStream.get(0);
    }
    public void save() {
        Collection<ServerEntity> collection = db.collection(ServerEntity.class);
        collection.save(all);
    }
}
