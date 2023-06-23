package union.xenfork.nucleoplasm.command.level;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import union.xenfork.nucleoplasm.command.level.command.HomeCommand;
import union.xenfork.nucleoplasm.command.level.command.SetHomeCommand;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            dispatcher.register(
                    literal("home")
                            .requires(source -> source.hasPermissionLevel(0))
                            .executes(new HomeCommand())
                            .then(
                                    argument("home_name", StringArgumentType.word())
                                            .executes(new HomeCommand())
                            )
                            .then(
                                    literal("set")
                                            .then(
                                                    argument("home_name", StringArgumentType.word())
                                                            .executes(new SetHomeCommand())
                                            )
                            )
            );
            dispatcher.register(
                    literal("sethome")
                            .requires(source -> source.hasPermissionLevel(0))
                            .then(
                                    argument("home_name", StringArgumentType.word())
                                            .executes(new SetHomeCommand())
                            )
            );
        });
    }
}
