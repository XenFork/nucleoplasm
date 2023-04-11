package union.xenfork.nucleoplasm.command.level.quickio;

import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.struct.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class GroupEntity extends IOEntity {
    public String group_name;
    public ArrayList<String> permission;
    private GroupEntity() {
        permission = new ArrayList<>();
    }
    public static GroupEntity of(Consumer<GroupEntity> consumer) {
        GroupEntity groupEntity = new GroupEntity();
        consumer.accept(groupEntity);
        return groupEntity;
    }
}
