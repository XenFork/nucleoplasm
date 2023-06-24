package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.face.EntityAccess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mixin(Entity.class)
public class MixinEntity implements EntityAccess {
    public Map<String, Double> homeX ,homeY, homeZ;
    public Map<String, Float> homeYaw,homePitch;
    public Map<String, String> serverWorldMap;

    @Override
    public void setHome(String homeIndex, ServerPlayerEntity player) {
        if (homeX == null) homeX = new HashMap<>();
        if (homeY == null) homeY = new HashMap<>();
        if (homeZ == null) homeZ = new HashMap<>();
        if (homeYaw == null) homeYaw = new HashMap<>();
        if (homePitch == null) homePitch = new HashMap<>();
        if (serverWorldMap == null) serverWorldMap = new HashMap<>();
        homeX.put(homeIndex, player.getX());
        homeY.put(homeIndex, player.getY());
        homeZ.put(homeIndex, player.getZ());
        homeYaw.put(homeIndex, player.getYaw());
        homePitch.put(homeIndex, player.getPitch());
        serverWorldMap.put(homeIndex, player.getServerWorld().asString());

    }

    @Override
    public void delHome(String homeIndex) {
        homeX.remove(homeIndex);
        homeY.remove(homeIndex);
        homeZ.remove(homeIndex);
        homeYaw.remove(homeIndex);
        homePitch.remove(homeIndex);
        serverWorldMap.remove(homeIndex);
    }

    @Override
    public void gotoHome(String homeIndex, ServerPlayerEntity player) {
        if (homeX.containsKey(homeIndex)) {
            for (ServerWorld world : player.server.getWorlds()) {
                if (world.asString().equals(serverWorldMap.get(homeIndex))) {
                    player.teleport(world, homeX.get(homeIndex), homeY.get(homeIndex), homeZ.get(homeIndex), homeYaw.get(homeIndex), homePitch.get(homeIndex));
                }
            }
        } else {
            player.sendMessage(Text.literal("don,t goto home%s".formatted(homeIndex)));
        }
    }
    @Override
    public Set<String> getHomes() {
        if (homeX != null) return homeX.keySet();
        return new HashSet<>();
    }
}
