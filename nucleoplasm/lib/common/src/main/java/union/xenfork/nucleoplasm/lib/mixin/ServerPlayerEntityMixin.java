package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.lib.event.EntityEvents;
import union.xenfork.nucleoplasm.lib.event.ItemEvents;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    private final ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
    @Inject(method = "dropSelectedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropSelectedItem(Z)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void dropItem(boolean entireStack,
                          CallbackInfoReturnable<Boolean> cir,
                          PlayerInventory playerInventory) {
        ActionResult result = ItemEvents.DROP_ITEM_EVENT.invoker().interact(player, playerInventory.getMainHandStack());
        if (result == ActionResult.FAIL) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void onPlayerInteractEntity(Entity target, CallbackInfo info) {
        ActionResult result = EntityEvents.ATTACK_ENTITY_EVENT.invoker().interact(player, player.getEntityWorld(), Hand.MAIN_HAND, target, null);
        if (result != ActionResult.PASS) {
            info.cancel();
        }
    }

}
