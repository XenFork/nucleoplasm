package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.lib.event.ActionEvents;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Shadow @Final protected ServerPlayerEntity player;

    @Shadow protected ServerWorld world;

    @Inject(at = @At("HEAD"), method = "processBlockBreakingAction", cancellable = true)
    public void startBlockBreak(BlockPos pos, PlayerActionC2SPacket.Action playerAction, Direction direction, int worldHeight, int i, CallbackInfo info) {
        if (playerAction != PlayerActionC2SPacket.Action.START_DESTROY_BLOCK) return;
        ActionResult result = ActionEvents.ATTACK_BLOCK_EVENT.invoker().interact(player, world, Hand.MAIN_HAND, pos, direction);

        if (result != ActionResult.PASS) {
            // The client might have broken the block on its side, so make sure to let it know.
            this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(world, pos));

            if (world.getBlockState(pos).hasBlockEntity()) {
                BlockEntity blockEntity = world.getBlockEntity(pos);

                if (blockEntity != null) {
                    Packet<ClientPlayPacketListener> updatePacket = blockEntity.toUpdatePacket();

                    if (updatePacket != null) {
                        this.player.networkHandler.sendPacket(updatePacket);
                    }
                }
            }

            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "interactItem", cancellable = true)
    public void interactItem(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        TypedActionResult<ItemStack> result = ActionEvents.USE_ITEM_EVENT.invoker().interact(player, world, hand);

        if (result.getResult() != ActionResult.PASS) {
            info.setReturnValue(result.getResult());
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "interactBlock", cancellable = true)
    public void interactBlock(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<ActionResult> info) {
        ActionResult result = ActionEvents.USE_BLOCK_EVENT.invoker().interact(player, world, hand, blockHitResult);

        if (result != ActionResult.PASS) {
            info.setReturnValue(result);
            info.cancel();
        }
    }
}
