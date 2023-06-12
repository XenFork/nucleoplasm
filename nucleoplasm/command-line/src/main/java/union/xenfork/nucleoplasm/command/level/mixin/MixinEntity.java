package union.xenfork.nucleoplasm.command.level.mixin;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.face.EntityAccessor;

import java.util.ArrayList;

@Debug(export = true)
@Mixin(value = Entity.class, remap = false)
public class MixinEntity implements EntityAccessor {
    public ArrayList<String> groups;
    public ArrayList<String> permissions;

    @Override
    public void addPermission(String permission) {
        if (permissions == null) groups = new ArrayList<>();
        groups.add(permission);
    }

    @Override
    public ArrayList<String> getPermissions() {
        return permissions;
    }

    @Override
    public void addGroup(String group) {
        if (groups == null) groups = new ArrayList<>();
        groups.add(group);
    }

    @Override
    public ArrayList<String> getGroups() {
        return groups;
    }
}
