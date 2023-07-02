package union.xenfork.nucleoplasm.api.mixin;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.event.EventResult;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.event.CommandEvent;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;execute(Lcom/mojang/brigadier/ParseResults;)I", remap = false), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void execute(ParseResults<ServerCommandSource> parseResults,
                         String command,
                         CallbackInfoReturnable<Integer> cir,
                         ServerCommandSource serverCommandSource) throws CommandSyntaxException {
        EventResult command1 = CommandEvent.COMMAND_EVENT.invoker().command(parseResults, command, serverCommandSource);
        if (command1.isPresent()) {
            if (command1.isFalse()) {
                cir.setReturnValue(0);
            } else {
                cir.setReturnValue(Command.SINGLE_SUCCESS);
            }
        }

    }
}
