package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.command.level.face.EntityAccess;
import union.xenfork.nucleoplasm.command.level.utils.IIdentifier;
import union.xenfork.nucleoplasm.command.level.utils.IVec2f;
import union.xenfork.nucleoplasm.command.level.utils.IVec3d;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Mixin(Entity.class)
public class MixinEntity implements EntityAccess {
    public HashMap<String, IVec3d> homeXYZ;
    public HashMap<String, IVec2f> homeYP;
    public HashMap<String, IIdentifier> homeWorldId;


    @Override
    public void setHome(String homeIndex, ServerPlayerEntity player) {
        if (homeXYZ == null) homeXYZ = new HashMap<>();
        if (homeYP == null) homeYP = new HashMap<>();
        if (homeWorldId == null) homeWorldId = new HashMap<>();
        homeXYZ.put(homeIndex, new IVec3d(player.getPos()));
        homeYP.put(homeIndex, new IVec2f(player.getYaw(), player.getPitch()));
        homeWorldId.put(homeIndex, new IIdentifier(player.getServerWorld().getRegistryKey().getValue()));
    }

    @Override
    public void delHome(String homeIndex) {
        homeXYZ.remove(homeIndex);
        homeYP.remove(homeIndex);
        homeWorldId.remove(homeIndex);
    }

    @Override
    public void gotoHome(String homeIndex, ServerPlayerEntity player) {

        if (homeXYZ.containsKey(homeIndex)) {
            for (ServerWorld world : player.server.getWorlds()) {
                Identifier value = world.getRegistryKey().getValue();
                IIdentifier iIdentifier = homeWorldId.get(homeIndex);
                if (value.getNamespace().equals(iIdentifier.getNamespace()) && value.getPath().equals(iIdentifier.getPath())) {
                    IVec3d vec3d = homeXYZ.get(homeIndex);
                    IVec2f iVec2f = homeYP.get(homeIndex);
                    player.teleport(world, vec3d.x, vec3d.y, vec3d.z, iVec2f.x, iVec2f.y);
                }
            }
        } else {
            player.sendMessage(Text.literal("don,t goto home%s".formatted(homeIndex)));
        }
    }
    @Override
    public Set<String> getHomes() {
        if (homeXYZ != null) return homeXYZ.keySet();
        return new HashSet<>();
    }
}
