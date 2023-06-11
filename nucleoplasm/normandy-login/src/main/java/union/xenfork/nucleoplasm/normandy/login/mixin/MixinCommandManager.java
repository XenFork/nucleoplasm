package union.xenfork.nucleoplasm.normandy.login.mixin;

import com.mojang.brigadier.ParseResults;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccessor;

@Mixin(CommandManager.class)
public class MixinCommandManager {
    @Inject(method = "execute", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;execute(Lcom/mojang/brigadier/ParseResults;)I", remap = false), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void execute(ParseResults<ServerCommandSource> parseResults,
                         String command,
                         CallbackInfoReturnable<Integer> cir,
                         ServerCommandSource serverCommandSource) {
        ServerPlayerEntity player = parseResults.getContext().getSource().getPlayer();
        if (player != null) {
            EntityImplAccessor impl = (EntityImplAccessor) NucleoplasmServer.impl;
            EntityAccessor accessor = (EntityAccessor) NucleoplasmServer.impl.find(player);

            boolean is_login = accessor.getIsLogin();
            if (!(command.contains("register") || command.contains("login")) && !is_login) {
                String password = accessor.getPassword();
                if (password == null || password.isEmpty()) {
                    player.sendMessage(Text.literal("You're not registered in yet and cannot use commands"));
                } else {
                    player.sendMessage(Text.literal("You're not logged in yet and cannot use commands"));
                }
                cir.setReturnValue(0);
            }
        }

    }
}
