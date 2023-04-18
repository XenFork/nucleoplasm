package union.xenfork.nucleoplasm.api.quickio;

import com.github.artbits.quickio.core.IOEntity;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GroupEntity extends IOEntity {
    public String group_name;
    public ArrayList<String> permission;
    public ArrayList<String> extends_group;
    public GroupEntity() {

    }
}
