package union.xenfork.nucleoplasm.command.level.core;

import com.github.artbits.quickio.core.IOEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class GroupEntity extends IOEntity {
    public String id;

    public ArrayList<String> group_names;
    public Map<String, ArrayList<String>> extends_groups;
    public Map<String, ArrayList<String>> group_permissions;

    public static GroupEntity of(Consumer<GroupEntity> consumer) {
        GroupEntity group = new GroupEntity();
        consumer.accept(group);
        return group;
    }
}
