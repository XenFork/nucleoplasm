package union.xenfork.nucleoplasm.command.level.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.serials.minecraft.util.math.IVec3d;
import org.spongepowered.asm.mixin.Mixin;
import union.xenfork.nucleoplasm.api.core.ServerEntity;
import union.xenfork.nucleoplasm.command.level.utils.IIdentifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mixin(ServerEntity.class)
public class MixinServerEntity {
    public Map<String, IVec3d> warpPos;
    public Map<String, IIdentifier> warpWorld;

    public void setWarp(String name, ServerPlayerEntity player) {
        if (warpPos == null) warpPos = new HashMap<>();
        if (warpWorld == null) warpWorld = new HashMap<>();
        warpPos.put(name, new IVec3d(player.getPos()));
        warpWorld.put(name, new IIdentifier(player.getServerWorld().getRegistryKey().getValue()));
    }

    public void setWarp(ServerPlayerEntity player) {
        if (warpPos == null) warpPos = new HashMap<>();
        if (warpWorld == null) warpWorld = new HashMap<>();
        for (int i = 0; true; i++) {
            String formatted = "warp_%d".formatted(i);
            if (!warpPos.containsKey(formatted)) {
                setWarp(formatted, player);
                break;
            }
        }
    }

    public void gotoWarp(String name, ServerPlayerEntity player) {
        if (warpPos.containsKey(name)) {
            for (ServerWorld world : player.server.getWorlds()) {
                Identifier value = world.getRegistryKey().getValue();
                IIdentifier iIdentifier = warpWorld.get(name);
                if (value.getNamespace().equals(iIdentifier.getNamespace()) && value.getPath().equals(iIdentifier.getPath())) {
                    IVec3d vec3d = warpPos.get(name);
                    player.teleport(world, vec3d.x, vec3d.y, vec3d.z, player.getYaw(), player.getPitch());
                    break;
                }
            }
        }
    }

    public Set<String> getWarp() {
        if (warpPos == null) return new HashSet<>();
        return warpPos.keySet();
    }
}
