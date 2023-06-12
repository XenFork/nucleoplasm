package union.xenfork.nucleoplasm.command.level.core;

import com.github.artbits.quickio.core.IOEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class GroupEntity extends IOEntity {
    public String group;
    public ArrayList<String> extends_group;
    public ArrayList<String> permissions;



    public static GroupEntity of(Consumer<GroupEntity> consumer) {
        GroupEntity group = new GroupEntity();
        consumer.accept(group);
        return group;
    }
}
