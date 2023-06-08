package union.xenfork.nucleoplasm.normandy.login;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmLoader;
import union.xenfork.nucleoplasm.normandy.login.common.LoginCommon;
import union.xenfork.nucleoplasm.normandy.login.common.RegisterCommon;
import union.xenfork.nucleoplasm.normandy.login.event.Server;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
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
                    .then(argument("password", greedyString())
                            .then(argument("verify_password", greedyString())
                                    .executes(new RegisterCommon())
                            )
                    )
            );
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("login")
                    .then(argument("password", greedyString())
                            .executes(new LoginCommon())
                    )
            );
        });
    }
}
