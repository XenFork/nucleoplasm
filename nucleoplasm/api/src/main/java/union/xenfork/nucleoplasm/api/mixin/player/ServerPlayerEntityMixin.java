package union.xenfork.nucleoplasm.api.mixin.player;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "dropItem", at = @At("HEAD"))
    private void dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        ActionResult result = ServerPlayerEvents.DROP_ITEM_EVENT.invoker().interact(player, stack);
        if (result == ActionResult.FAIL) {
            cir.cancel();
        }
    }
}
