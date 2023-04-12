package union.xenfork.nucleoplasm.command.level.server;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.command.level.quickio.GroupEntity;
import union.xenfork.nucleoplasm.command.level.quickio.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class NucleoplasmCommandLevelServer implements DedicatedServerModInitializer {
    public static final DB player = QuickIO.usingDB("player");
    public static final DB group = QuickIO.usingDB("group");
    @Override
    public void onInitializeServer() {
        Collection<GroupEntity> groupConn = group.collection(GroupEntity.class);
        groupConn.save(GroupEntity.of(groupEntity -> {
            groupEntity.group_name = "default";
            groupEntity.permission = new ArrayList<>();
        }));
        ServerPlayerEvents.LOGIN_EVENT.register(serverPlayer -> {
            Collection<PlayerEntity> collection = player.collection(PlayerEntity.class);
            List<PlayerEntity> all = collection.findAll();
            boolean b = true;
            for (PlayerEntity playerEntity : all) {
                if (playerEntity.player_name.equals(serverPlayer.getEntityName())) {
                    b = false;
                }
            }
            if (b) {
                collection.save(PlayerEntity.of(playerEntity -> {
                    playerEntity.player_name = serverPlayer.getEntityName();
                    playerEntity.groups = new ArrayList<>();
                }));
            }
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            player.close();
            group.close();
        });
    }
}
