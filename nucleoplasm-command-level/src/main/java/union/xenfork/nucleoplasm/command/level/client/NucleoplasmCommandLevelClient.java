package union.xenfork.nucleoplasm.command.level.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import union.xenfork.nucleoplasm.api.event.PlayerEvents;

import java.util.ArrayList;

import static union.xenfork.nucleoplasm.command.level.server.NucleoplasmCommandLevelServer.groupDB;
import static union.xenfork.nucleoplasm.command.level.server.NucleoplasmCommandLevelServer.playerDB;

public class NucleoplasmCommandLevelClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerEvents.TICK_EVENT.register(entity -> {
            double x = entity.capeX;
            double y = entity.capeY;
            double z = entity.capeZ;
            if (playerDB != null && groupDB != null) {
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
            }
        });
    }
}
