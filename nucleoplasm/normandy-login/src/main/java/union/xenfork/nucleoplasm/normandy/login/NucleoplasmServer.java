package union.xenfork.nucleoplasm.normandy.login;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import union.xenfork.nucleoplasm.normandy.login.command.ChangeCommand;
import union.xenfork.nucleoplasm.normandy.login.command.LoginCommand;
import union.xenfork.nucleoplasm.normandy.login.command.LogoutCommand;
import union.xenfork.nucleoplasm.normandy.login.command.RegisterCommand;

import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.command.argument.EntityArgumentType.player;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
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
            LogoutCommand command = new LogoutCommand();
            dispatcher.register(literal("logout")
                    .requires(source -> source.hasPermissionLevel(1))
                    .executes(command)
                    .then(argument("player", player())
                            .executes(command)
                    )
            );
        });
    }
}
