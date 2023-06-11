package union.xenfork.nucleoplasm.command.level.mixin;

import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.face.EntityAccessor;

import java.util.ArrayList;

@Mixin(Entity.class)
public class MixinEntity implements EntityAccessor {
    public ArrayList<String> groups;
    
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
