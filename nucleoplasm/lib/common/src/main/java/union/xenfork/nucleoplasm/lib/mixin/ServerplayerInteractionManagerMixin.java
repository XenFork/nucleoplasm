package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.lib.event.ActionEvents;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerplayerInteractionManagerMixin {
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
}
