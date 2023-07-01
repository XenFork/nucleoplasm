package union.xenfork.nucleoplasm.lib.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.lib.event.EntityEvents;
import union.xenfork.nucleoplasm.lib.event.BlockEvents;
import union.xenfork.nucleoplasm.lib.event.ItemEvents;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract void sendSequencedPacket(ClientWorld world, SequencedPacketCreator packetCreator);

    @Shadow private GameMode gameMode;

    @Shadow @Final private ClientPlayNetworkHandler networkHandler;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameMode;isCreative()Z", ordinal = 0), method = "attackBlock", cancellable = true)
    public void attackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> info) {
        callback(pos, direction, info);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameMode;isCreative()Z", ordinal = 0), method = "updateBlockBreakingProgress", cancellable = true)
    public void method_2902(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> info) {
        if (gameMode.isCreative()) {
            callback(pos, direction, info);
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V", ordinal = 0), method = "attackEntity", cancellable = true)
    public void attackEntity(PlayerEntity player, Entity entity, CallbackInfo info) {
        ActionResult result = EntityEvents.ATTACK_ENTITY_EVENT.invoker().interact(player, player.getEntityWorld(), Hand.MAIN_HAND /* TODO */, entity, null);

        if (result != ActionResult.PASS) {
            if (result == ActionResult.SUCCESS) {
                this.networkHandler.sendPacket(PlayerInteractEntityC2SPacket.attack(entity, player.isSneaking()));
            }

            info.cancel();
        }
    }

    @Unique
    private void callback(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> info) {
        ActionResult result = BlockEvents.ATTACK_BLOCK_EVENT.invoker().interact(client.player, client.world, Hand.MAIN_HAND, pos, direction);

        if (result != ActionResult.PASS) {
            // Returning true will spawn particles and trigger the animation of the hand -> only for SUCCESS.
            info.setReturnValue(result == ActionResult.SUCCESS);

            // We also need to let the server process the action if it's accepted.
            if (result.isAccepted()) {
                sendSequencedPacket(client.world, id -> new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction, id));
            }
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V", ordinal = 0), method = "interactItem", cancellable = true)
    public void interactItem(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        // hook interactBlock between the spectator check and sending the first packet to invoke the use item event first
        // this needs to be in interactBlock to avoid sending a packet in line with the event javadoc
        TypedActionResult<ItemStack> result = ItemEvents.USE_ITEM_EVENT.invoker().interact(player, player.getWorld(), hand);

        if (result.getResult() != ActionResult.PASS) {
            if (result.getResult() == ActionResult.SUCCESS) {
                // send the move packet like vanilla to ensure the position+view vectors are accurate
                networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch(), player.isOnGround()));
                // send interaction packet to the server with a new sequentially assigned id
                sendSequencedPacket((ClientWorld) player.getWorld(), id -> new PlayerInteractItemC2SPacket(hand, id));
            }
            info.setReturnValue(result.getResult());
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;sendSequencedPacket(Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/client/network/SequencedPacketCreator;)V"), method = "interactBlock", cancellable = true)
    public void interactBlock(ClientPlayerEntity player, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> info) {

        ActionResult result = BlockEvents.USE_BLOCK_EVENT.invoker().interact(player, player.getWorld(), hand, blockHitResult);

        if (result != ActionResult.PASS) {
            if (result == ActionResult.SUCCESS) {
                sendSequencedPacket(player.clientWorld, id -> new PlayerInteractBlockC2SPacket(hand, blockHitResult, id));
            }
            info.setReturnValue(result);
        }
    }
}
