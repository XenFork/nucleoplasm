package union.xenfork.nucleoplasm.api.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "playerTick", at = @At(value = "HEAD"))
    public void tick(CallbackInfo ci) {
        ServerPlayerEvents.PLAYER_TICK_EVENT.invoker().tick((ServerPlayerEntity) (Object)this);
    }


}
