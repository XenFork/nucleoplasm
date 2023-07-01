package union.xenfork.nucleoplasm.lib.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.lib.event.EntityEvents;
import union.xenfork.nucleoplasm.lib.event.ItemEvents;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Nullable public ClientPlayerEntity player;

    @Shadow @Nullable public abstract ClientPlayNetworkHandler getNetworkHandler();

    @SuppressWarnings("DataFlowIssue")
    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/network/ClientPlayerInteractionManager.interactEntityAtLocation(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/hit/EntityHitResult;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"
            ),
            method = "doItemUse",
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void injectUseEntityCallback(CallbackInfo ci, Hand[] hands, int i1, int i2, Hand hand, ItemStack stack, EntityHitResult hitResult, Entity entity) {
        ActionResult result = EntityEvents.USE_ENTITY_EVENT.invoker().interact(player, player.getEntityWorld(), hand, entity, hitResult);

        if (result != ActionResult.PASS) {
            if (result.isAccepted()) {
                Vec3d hitVec = hitResult.getPos().subtract(entity.getX(), entity.getY(), entity.getZ());
                getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.interactAt(entity, player.isSneaking(), hand, hitVec));
            }

            if (result.shouldSwingHand()) {
                player.swingHand(hand);
            }
            ci.cancel();
        }
    }

    @Redirect(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;wasPressed()Z", ordinal = 7))
    private boolean dropItem(KeyBinding instance) {
        if (player != null) {
            ActionResult result = ItemEvents.DROP_ITEM_EVENT.invoker().interact(player, player.getMainHandStack());
            if (result != ActionResult.PASS) {
                return false;
            }
        }
        return instance.wasPressed();
    }
}
