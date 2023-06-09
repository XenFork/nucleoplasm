package union.xenfork.nucleoplasm.normandy.login;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmLoader;
import union.xenfork.nucleoplasm.normandy.login.command.ChangeCommand;
import union.xenfork.nucleoplasm.normandy.login.command.LoginCommand;
import union.xenfork.nucleoplasm.normandy.login.command.LogoutCommand;
import union.xenfork.nucleoplasm.normandy.login.command.RegisterCommand;
import union.xenfork.nucleoplasm.normandy.login.event.Server;

import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    public static final String mod_id = "nucleoplasm_normandy_login";
    public static final Logger logger = LoggerFactory.getLogger(mod_id);
    public static final NucleoplasmLoader<NucleoplasmEntity> nnl = new NucleoplasmLoader<>("login", NucleoplasmEntity.class);

    @Override
    public void onInitializeServer() {
        Server.init(nnl);
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
