package union.xenfork.nucleoplasm.command.level.face;

import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.core.GroupEntity;

import java.util.List;

public interface EntityImplAccessor {
    default void save(Entity entity) {}
    default List<String> getPermissions(){throw new AssertionError();}

}
