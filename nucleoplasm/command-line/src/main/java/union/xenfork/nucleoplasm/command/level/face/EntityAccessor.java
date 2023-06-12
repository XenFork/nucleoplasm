package union.xenfork.nucleoplasm.command.level.face;

import java.util.ArrayList;

public interface EntityAccessor {
    default void addGroup(String group) {}
    default void addPermission(String permission){}
    default ArrayList<String> getGroups() {throw new AssertionError();}
    default ArrayList<String> getPermissions() {throw new AssertionError();}
}
