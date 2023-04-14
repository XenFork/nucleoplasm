package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;

public class PlayerEvents {

    public static final Event<PlayerTick> TICK_EVENT = EventFactory.createArrayBacked(PlayerTick.class,
            callbacks -> entity -> {
                for (PlayerTick callback : callbacks) {
                    callback.tick(entity);
                }
            });

    public interface PlayerTick {
        void tick(ClientPlayerEntity entity);
    }
}
