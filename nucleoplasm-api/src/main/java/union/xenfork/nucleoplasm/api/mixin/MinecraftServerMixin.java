package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow private PlayerManager playerManager;


    @Shadow public abstract Iterable<ServerWorld> getWorlds();

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
    }

    @Inject(method = "tickWorlds", at = @At("TAIL"))
    public void tickWorlds(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        for (ServerWorld world : getWorlds()) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                ServerPlayerEvents.PLAYER_TICK_EVENT.invoker().tick(player);
            }
        }
    }
}
