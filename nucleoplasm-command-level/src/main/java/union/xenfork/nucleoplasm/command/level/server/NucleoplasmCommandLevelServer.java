package union.xenfork.nucleoplasm.command.level.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;
import union.xenfork.nucleoplasm.api.quickio.GroupEntity;
import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;
import union.xenfork.nucleoplasm.api.quickio.utils.GroupDB;
import union.xenfork.nucleoplasm.api.quickio.utils.PlayerDB;

import java.util.ArrayList;
import java.util.List;

public final class NucleoplasmCommandLevelServer implements DedicatedServerModInitializer {
    public static PlayerDB<PlayerEntity> playerDB;
    public static GroupDB<GroupEntity> groupDB;
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            playerDB = new PlayerDB<>("player", PlayerEntity.class);
            groupDB = new GroupDB<>("group", GroupEntity.class);
            //noinspection deprecation
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
            if (!playerDB.hasPlayer(serverPlayer)) {
                playerDB.add(PlayerEntity.of(playerEntity -> {
                    playerEntity.player_name = serverPlayer.getEntityName();
                    //noinspection deprecation
                    ArrayList<String> groups = new ArrayList<>(playerDB.getGroups("default"));
                    groups.add(serverPlayer.getEntityName());
                    playerEntity.groups = groups;
                    playerEntity.join_time = serverPlayer.server.getTimeReference();
                }));
                groupDB.add(GroupEntity.of(entity -> {
                    entity.group_name = serverPlayer.getEntityName() + "-private-group";
                    entity.permission = new ArrayList<>();
                    entity.extends_group = new ArrayList<>();
                }));
            }
        });

        ServerPlayerEvents.PLAYER_TICK_EVENT.register(entity -> {
            double x = entity.capeX;
            double y = entity.capeY;
            double z = entity.capeZ;
            ArrayList<String> groups =
                    playerDB.getGroups(entity);

            boolean hasMove = false;
            if (playerDB.isLogin(entity)) for (String group : groups) {
                ArrayList<String> groups1 = groupDB.getGroups(group);
                for (String s : groups1)
                    if (s.equals("minecraft.move")) {
                        hasMove = true;
                        break;
                    }
            }
            if (!hasMove) {
                entity.move(MovementType.SELF, new Vec3d(x, y, z));
            }
        });

        ServerPlayerEvents.LOGIN_OUT_EVENT.register(serverPlayer -> {
            playerDB.isLogin(serverPlayer);
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            playerDB.close();
            groupDB.close();
        });
    }
}
