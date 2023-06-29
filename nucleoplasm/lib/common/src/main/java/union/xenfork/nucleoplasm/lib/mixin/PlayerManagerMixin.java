package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.lib.event.PlayerLoginsEvents;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    private void login(ClientConnection connection,
                       ServerPlayerEntity player,
                       CallbackInfo ci) {
        PlayerLoginsEvents.SERVER_PLAYER_LOGIN_EVENT.invoker().login(player);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void login_out(ServerPlayerEntity player,
                           CallbackInfo ci) {
        PlayerLoginsEvents.SERVER_PLAYER_LOGIN_OUT_EVENT.invoker().login_out(player);
    }
}
