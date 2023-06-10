package union.xenfork.nucleoplasm.normandy.login.mixin;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.normandy.login.command.*;

import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@Mixin(NucleoplasmServer.class)
public class MixinNucleoplasmServer {
    @Inject(method = "onInitializeServer", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("register")
                    .then(argument("password", word())
                            .then(argument("confirm_password", word())
                                    .executes(new RegisterCommand())
                            )
                    )
            );
            dispatcher.register(literal("login")
                    .then(argument("password", word())
                            .executes(new LoginCommand())
                    )
            );
            dispatcher.register(literal("change")
                    .then(argument("old_password", word())
                            .then(argument("new_password", word())
                                    .executes(new ChangeCommand())
                            )
                    )
            );
            dispatcher.register(literal("logout")
                    .requires(source -> source.hasPermissionLevel(1))
                    .executes(new LogoutCommand())
            );
        });
    }
}
