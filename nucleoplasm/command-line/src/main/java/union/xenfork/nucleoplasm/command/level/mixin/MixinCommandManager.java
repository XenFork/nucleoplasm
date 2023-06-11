package union.xenfork.nucleoplasm.command.level.mixin;

import com.mojang.brigadier.ParseResults;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = CommandManager.class, priority = 999)
public class MixinCommandManager {
    @Inject(
            method = "execute",
            at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;execute(Lcom/mojang/brigadier/ParseResults;)I", remap = false),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private void execute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfoReturnable<Integer> cir, ServerCommandSource serverCommandSource) {
        ServerPlayerEntity player = parseResults.getContext().getSource().getPlayer();
        if (player != null) {

        }
    }
}
