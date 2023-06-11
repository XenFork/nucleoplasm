package union.xenfork.nucleoplasm.command.level.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.face.EntityImplAccessor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupEntityImpl {
    private final DB qdb;

    public ArrayList<String> group_names;
    public Map<String, ArrayList<String>> extends_groups;
    public Map<String, ArrayList<String>> group_permissions;

    public GroupEntityImpl(Path path) {
        Config config = Config.of(c -> {
            c.name("group");
            c.path(path.toFile().getAbsolutePath());
            c.cache(16L * 1024 * 1024);
        });
        qdb = QuickIO.usingDB(config);
    }

    public void create() {
        Collection<GroupEntity> collection = qdb.collection(GroupEntity.class);
        GroupEntity of = GroupEntity.of(entity -> {
            entity.id = "group";
            entity.group_names = new ArrayList<>();
            entity.extends_groups = new HashMap<>();
            entity.group_permissions = new HashMap<>();
            entity.group_names.add("default");
            entity.extends_groups.put("default", new ArrayList<>());
            entity.group_permissions.put("default", new ArrayList<>());
        });
        collection.save(of);
    }

    public void update() {
        Collection<GroupEntity> collection = qdb.collection(GroupEntity.class);
        GroupEntity group = collection.findOne(entity -> entity.id.equals("group"));
        group_names = group.group_names;
        extends_groups = group.extends_groups;
        group_permissions = group.group_permissions;
    }

    public void save() {
        Collection<GroupEntity> collection = qdb.collection(GroupEntity.class);
        GroupEntity group = collection.findOne(entity -> entity.id.equals("group"));
        group.group_names = group_names;
        group.group_permissions = group_permissions;
        group.extends_groups = extends_groups;
    }
}
