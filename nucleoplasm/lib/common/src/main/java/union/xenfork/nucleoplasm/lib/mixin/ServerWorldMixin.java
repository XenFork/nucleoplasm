package union.xenfork.nucleoplasm.lib.mixin;

import net.minecraft.server.world.ServerWorld;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.lib.event.ServerWorldTickEvents;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/server/world/ServerWorld;inBlockTick:Z", opcode = Opcodes.PUTFIELD, ordinal = 0, shift = At.Shift.AFTER))
    private void startWorldTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ServerWorldTickEvents.WORLD_TICK_EVENT.invoker().tick((ServerWorld) (Object) this);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void endWorldTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ServerWorldTickEvents.END_WORLD_TICK_EVENT.invoker().tick((ServerWorld) (Object) this);
    }
}
