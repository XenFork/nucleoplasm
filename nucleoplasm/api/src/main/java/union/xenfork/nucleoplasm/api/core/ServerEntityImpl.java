package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.server.MinecraftServer;

import java.nio.file.Path;
import java.util.List;

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
        var serverEntityStream = all.stream().filter(serverEntity -> serverEntity.motd.equals(server.getName())).toList();
        if (serverEntityStream.isEmpty()) {
            ServerEntity of = ServerEntity.of(serverEntity -> {
                serverEntity.motd = server.getServerMotd();
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

    public void delete(MinecraftServer server) {
        ServerEntity serverEntity = find(server);
        all.remove(serverEntity);
    }

    public void delete(String motdName) {
        for (ServerEntity serverEntity : all) {
            if (serverEntity.motd.equals(motdName)) {
                all.remove(serverEntity);
                break;
            }
        }
    }
    //也可以用于备份还原
    //在修改motd前copy一下
    public void copyTo(MinecraftServer server, String motdName) {
        ServerEntity serverEntity = find(server).copy();
        serverEntity.motd = motdName;
        all.add(serverEntity);
    }
    // 换motd时没有加载上面命令产生的错误，从老td里面返回来
    public void copyBack(MinecraftServer server, String motdName) {
        for (ServerEntity serverEntity : all) {
            if (serverEntity.motd.equals(motdName)) {
                ServerEntity copy = serverEntity.copy();
                copy.motd = server.getServerMotd();
                delete(find(server).motd);
                all.add(copy);
            }
        }
    }
    //放弃修改还原到上次版本
    public void update() {
        all = db.collection(ServerEntity.class).findAll();
    }

}
