package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

@Mixin(CommandManager.class)
public class MixinCommandManager {
    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;execute(Lcom/mojang/brigadier/ParseResults;)I"), cancellable = true, remap = false, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void execute(ParseResults<ServerCommandSource> parseResults,
                         String command,
                         CallbackInfoReturnable<Integer> cir,
                         ServerCommandSource serverCommandSource) {
        ServerPlayerEntity player = parseResults.getContext().getSource().getPlayer();
        if (player != null) {
            NucleoplasmEntity entity = NucleoplasmServer.nnl.findEntity(player);
            if (!(command.contains("register") || command.contains("login")) && !entity.is_login) {
                cir.setReturnValue(0);
            }
        }

    }
}
