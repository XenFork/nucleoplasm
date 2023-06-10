package union.xenfork.nucleoplasm.api.mixin.player;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    private final ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
    @Inject(method = "dropSelectedItem", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void dropSelectItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
        ActionResult result = ServerPlayerEvents.DROP_ITEM_EVENT.invoker().interact(player, player.getInventory().getMainHandStack());
        if (result == ActionResult.FAIL) {
            cir.setReturnValue(false);
        }
    }
}
