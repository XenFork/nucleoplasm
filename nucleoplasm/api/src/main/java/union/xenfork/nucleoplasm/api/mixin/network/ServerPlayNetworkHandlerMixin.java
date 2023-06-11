package union.xenfork.nucleoplasm.api.mixin.network;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.event.ServerPlayerEvents;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(
            method = "onChatMessage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;validateMessage(Ljava/lang/String;Ljava/time/Instant;Lnet/minecraft/network/message/LastSeenMessageList$Acknowledgment;)Ljava/util/Optional;",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void chat(ChatMessageC2SPacket packet, CallbackInfo ci) {
        ActionResult result = ServerPlayerEvents.CHAT_EVENT.invoker().chat(player);
        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }

    @Inject(
            method = "onPlayerAction",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/server/world/ServerWorld;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void action(PlayerActionC2SPacket packet,
                        CallbackInfo ci) {
        if (packet.getAction() == PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND) {
            ActionResult result = ServerPlayerEvents.TAKE_EVENT.invoker().take(player);
            if (result == ActionResult.FAIL) {
                ci.cancel();
            }
        } else if (packet.getAction() == PlayerActionC2SPacket.Action.DROP_ITEM) {
            ActionResult result = ServerPlayerEvents.DROP_ITEM_EVENT.invoker().interact(player);
            if (result == ActionResult.FAIL) {
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "onPlayerMove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/server/world/ServerWorld;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void move(PlayerMoveC2SPacket packet, CallbackInfo ci) {
        ActionResult result = ServerPlayerEvents.MOVE_EVENT.invoker().move(player);
        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }

    @Inject(
            method = "onCreativeInventoryAction",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/server/world/ServerWorld;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void createInv(CreativeInventoryActionC2SPacket packet, CallbackInfo ci) {
        ActionResult result = ServerPlayerEvents.TAKE_EVENT.invoker().take(player);
        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
