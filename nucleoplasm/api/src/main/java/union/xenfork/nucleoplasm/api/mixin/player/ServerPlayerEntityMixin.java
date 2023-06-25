package union.xenfork.nucleoplasm.api.mixin.player;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.event.NbtRWEvents;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    private final ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
    @Inject(method = "dropSelectedItem", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void dropSelectItem(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
        ActionResult result = ServerPlayerEvents.DROP_ITEM_EVENT.invoker().interact(player, player.getInventory().getMainHandStack());
        if (result == ActionResult.FAIL) {
            player.giveItemStack(player.getInventory().getMainHandStack());
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void read(NbtCompound nbt, CallbackInfo ci) {
        NbtRWEvents.readServerPlayerNbtEvent.invoker().read(nbt, player);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void write(NbtCompound nbt, CallbackInfo ci) {
        NbtRWEvents.writeServerPlayerNbtEvent.invoker().write(nbt, player);
    }
}
