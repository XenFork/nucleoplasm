package union.xenfork.nucleoplasm.api.mixin.player;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    public void playerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        ServerPlayerEvents.LOGIN_EVENT.invoker().login(player);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    public void playerDisConnect(ServerPlayerEntity player, CallbackInfo ci) {
        ServerPlayerEvents.LOGIN_OUT_EVENT.invoker().login_out(player);
    }

}
