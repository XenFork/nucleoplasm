package union.xenfork.nucleoplasm.command.level.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.face.EntityImplAccessor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupEntityImpl {
    private final DB qdb;

    private List<GroupEntity> list;

    public GroupEntityImpl(Path path) {
        Config config = Config.of(c -> {
            c.name("group");
            c.path(path.toFile().getAbsolutePath());
            c.cache(16L * 1024 * 1024);
        });
        qdb = QuickIO.usingDB(config);
        Collection<GroupEntity> collection = qdb.collection(GroupEntity.class);
        list = collection.findAll();
        if (list.isEmpty()) {
            create();
        }
    }

    public GroupEntity find(String group) {
        List<GroupEntity> list1 = list.stream().filter(entity -> entity.group.equals(group)).toList();
        if (!list1.isEmpty()) {
            return list1.get(0);
        }
        return null;
    }

    public void update() {
        Collection<GroupEntity> collection = qdb.collection(GroupEntity.class);
        list = collection.findAll();
    }

    public void save() {
        qdb.collection(GroupEntity.class).save(list);
    }

    public void create() {
        Collection<GroupEntity> collection = qdb.collection(GroupEntity.class);
        GroupEntity def = GroupEntity.of(entity -> {
            entity.group = "default";
            entity.extends_group = new ArrayList<>();
            entity.permissions = new ArrayList<>();

        });
        GroupEntity build = GroupEntity.of(entity -> {
            entity.group = "builder";
            entity.extends_group = new ArrayList<>();
            entity.extends_group.add("default");
            entity.permissions = new ArrayList<>();
            entity.permissions.add("minecraft.attack.*");
            entity.permissions.add("minecraft.pic.up");
            entity.permissions.add("minecraft.break.block");
            entity.permissions.add("minecraft.use.*");
        });
        list.add(def);
        list.add(build);
        collection.save(list);
    }

    public void save(MinecraftServer server) {
        save();
    }

    public void close(MinecraftServer server) {
        close();
    }

    private void close() {
        qdb.close();
    }

    public void tick(ServerWorld world) {
        long timeReference = world.getServer().getTimeReference();
        if (timeReference % 10000 == 0) {
            save();
        }
    }

    public List<String> getPermission(String... groups) {
        List<String> permissions = new ArrayList<>();
        for (String group : groups) {
            List<GroupEntity> list1 = list.stream().filter(entity -> entity.group.equals(group)).toList();
            if (!list1.isEmpty()) {
                GroupEntity groupEntity = list1.get(0);
                for (String s : groupEntity.extends_group) {
                    permissions.addAll(getPermission(s));
                }
                permissions.addAll(groupEntity.permissions);
            }
        }
        return permissions;
    }

}
