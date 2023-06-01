package union.xenfork.nucleoplasm.api.mixin.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.ItemEvents;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    private void pickupItem(PlayerEntity player, CallbackInfo ci) {
        ActionResult result = ItemEvents.PICK_ITEM_EVENT.invoker().interact(player, (ItemEntity) (Object) this);
        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
