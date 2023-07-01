package union.xenfork.nucleoplasm.lib.mixin;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.GiveCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.lib.event.CommandEvents;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;execute(Lcom/mojang/brigadier/ParseResults;)I", remap = false), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void execute(ParseResults<ServerCommandSource> parseResults,
                         String command,
                         CallbackInfoReturnable<Integer> cir,
                         ServerCommandSource serverCommandSource) {
        try {
            CommandEvents.EXECUTE_EVENT.invoker().execute(parseResults, command, serverCommandSource);
        } catch (CommandSyntaxException ignored) {}
        GiveCommand
    }
}
