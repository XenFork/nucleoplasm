package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;
import union.xenfork.nucleoplasm.normandy.login.quickio.nnl.NNLPlayerEntity;

@Mixin(value = CommandDispatcher.class)
public class MixinServerCommand {
    @SuppressWarnings("unchecked")
    @Inject(method = "execute(Lcom/mojang/brigadier/ParseResults;)I", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/Command;run(Lcom/mojang/brigadier/context/CommandContext;)I"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true, remap = false)
    private <S> void execute(ParseResults<S> parse, CallbackInfoReturnable<Integer> cir, boolean forked, boolean foundCommand, CommandContext<S> context) {
        if (context.getSource() instanceof ServerCommandSource) {
            CommandContext<ServerCommandSource> context1 = (CommandContext<ServerCommandSource>) context;
            ServerPlayerEntity player = context1.getSource().getPlayer();
            NNLPlayerEntity entity = NucleoplasmServer.nnlPlayerDB.findEntity(player);
            if (!entity.isLogin) {
                cir.setReturnValue(0);
            }
        }
    }
}
