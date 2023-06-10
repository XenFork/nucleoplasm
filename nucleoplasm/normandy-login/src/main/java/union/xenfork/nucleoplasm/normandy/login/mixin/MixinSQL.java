package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.github.artbits.quickio.api.Collection;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.core.EntityImpl;

@Mixin(EntityImpl.class)
public class MixinSQL {
    @Inject(method = "login", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void login(ServerPlayerEntity entity,
                       CallbackInfo ci,
                       Collection<Entity> collection,
                       Entity one) {
        try {
            one.getClass().getDeclaredField("is_login").set(one, false);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }

    @Inject(method = "logout", at = @At(value = "INVOKE", target = "Lcom/github/artbits/quickio/api/Collection;save(Lcom/github/artbits/quickio/core/IOEntity;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void logout(ServerPlayerEntity entity,
                        CallbackInfo ci,
                        Collection<Entity> collection,
                        Entity one) {
        try {
            one.getClass().getDeclaredField("is_login").set(one, false);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }
}
