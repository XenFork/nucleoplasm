package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.core.IOEntity;
import net.serials.minecraft.util.math.IVec3d;

import java.util.UUID;
import java.util.function.Consumer;

public class Entity extends IOEntity {
    //玩家名称
    public String player_name;
    //玩家的uuid
    public UUID uuid;
    public IVec3d pos;

    public static Entity of(Consumer<Entity> consumer) {
        Entity entity = new Entity();
        consumer.accept(entity);
        return entity;
    }
}
