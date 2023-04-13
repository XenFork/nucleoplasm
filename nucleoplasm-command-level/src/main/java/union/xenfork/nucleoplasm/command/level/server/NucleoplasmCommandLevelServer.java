package union.xenfork.nucleoplasm.command.level.server;

import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.api.quickio.GroupEntity;
import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;
import union.xenfork.nucleoplasm.api.quickio.utils.GroupDB;
import union.xenfork.nucleoplasm.api.quickio.utils.PlayerDB;

import java.util.ArrayList;
import java.util.List;

public class NucleoplasmCommandLevelServer implements DedicatedServerModInitializer {
    public static PlayerDB<PlayerEntity> playerDB;
    public static GroupDB<GroupEntity> groupDB;
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            playerDB = new PlayerDB<>("player", PlayerEntity.class);
            groupDB = new GroupDB<>("group", GroupEntity.class);
            if (!playerDB.hasPlayer("default")) {
                playerDB.add(PlayerEntity.of(entity -> {
                    entity.player_name = "default";
                    entity.groups = new ArrayList<>(List.of(
                            "builder"
                    ));
                }));
            }
            if (groupDB.size() == 0) {
                groupDB.add(GroupEntity.of(entity -> {
                    entity.group_name = "default";
                    entity.permission = new ArrayList<>(List.of(
                            "minecraft.move"
                    ));
                    entity.extends_group = new ArrayList<>();
                }));
                groupDB.add(GroupEntity.of(entity -> {
                    entity.group_name = "builder";
                    entity.permission = new ArrayList<>(List.of(
                            "minecraft.break",//破坏
                            "minecraft.place",//放置
                            "minecraft.use",//使用
                            "minecraft.eat"//食用
                    ));
                    entity.extends_group = new ArrayList<>(List.of(
                            "default"
                    ));
                }));
                groupDB.add(GroupEntity.of(entity -> {
                    entity.group_name = "admin";
                    entity.permission = new ArrayList<>(List.of(
                            "*"
                    ));
                    entity.extends_group = new ArrayList<>(List.of(
                            "builder"
                    ));
                }));
            }
        });


        ServerPlayerEvents.LOGIN_EVENT.register(serverPlayer -> {
            if (!playerDB.hasPlayer(serverPlayer.getEntityName())) {
                playerDB.add(PlayerEntity.of(playerEntity -> {
                    playerEntity.player_name = serverPlayer.getEntityName();
                    playerEntity.groups = new ArrayList<>(playerDB.getGroups("default"));
                }));
            }
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            playerDB.close();
            groupDB.close();
        });
    }
}
