package union.xenfork.nucleoplasm.command.level.face;

import java.util.ArrayList;

public interface EntityAccessor {
    default void addGroup(String group) {}
    default ArrayList<String> getGroups() {throw new AssertionError();}
}
