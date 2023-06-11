package union.xenfork.nucleoplasm.command.level.face;

import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.core.GroupEntity;

public interface EntityImplAccessor {
    default void save(Entity entity) {}

}
