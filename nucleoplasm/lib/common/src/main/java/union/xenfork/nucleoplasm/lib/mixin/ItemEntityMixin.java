package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.lib.event.ItemEvents;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Inject(method = "onPlayerCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;getStack()Lnet/minecraft/item/ItemStack;"), cancellable = true)
    private void pickupItem(PlayerEntity player, CallbackInfo ci) {
        if (player != null) {
            ActionResult result = ItemEvents.PICK_ITEM_EVENT.invoker().interact(player, (ItemEntity) (Object) this);
            if (result == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }
}
