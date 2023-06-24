package union.xenfork.nucleoplasm.command.level;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.command.level.command.homes.DelHomeCommand;
import union.xenfork.nucleoplasm.command.level.command.homes.HomeCommand;
import union.xenfork.nucleoplasm.command.level.command.homes.SetHomeCommand;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class NucleoplasmServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            LiteralArgumentBuilder<ServerCommandSource> gohome = literal("gohome");
            LiteralArgumentBuilder<ServerCommandSource> sethome = literal("sethome");
            LiteralArgumentBuilder<ServerCommandSource> delhome = literal("delhome");
            RequiredArgumentBuilder<ServerCommandSource, String> homeName = argument("home_name", StringArgumentType.word());
            dispatcher.register(
                    gohome
                            .requires(source -> source.hasPermissionLevel(0))
                            .then(
                                    homeName
                                            .executes(new HomeCommand())
                            )
            );
            dispatcher.register(
                    literal("listhome")
                            .requires(source -> source.hasPermissionLevel(0))
                            .executes(new HomeCommand())
            );
            dispatcher.register(
                    literal("home")
                            .requires(source -> source.hasPermissionLevel(0))
                            .executes(context -> {
                                ServerPlayerEntity player = context.getSource().getPlayer();
                                if (player != null) {
                                    player.sendMessage(Text.literal("home help:"));
                                    player.sendMessage(Text.literal("\tgohome <home_name>"));
                                    player.sendMessage(Text.literal("\tlisthome"));
                                    player.sendMessage(Text.literal("\tdelhome <home_name>"));
                                    player.sendMessage(Text.literal("\tsethome <home_name>"));
                                    return Command.SINGLE_SUCCESS;
                                } else {
                                    throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
                                }
                            })
            );

            dispatcher.register(
                    sethome
                            .requires(source -> source.hasPermissionLevel(0))
                            .then(
                                    homeName
                                            .executes(new SetHomeCommand())
                            )
            );

            dispatcher.register(
                    delhome
                            .requires(source -> source.hasPermissionLevel(0))
                            .then(
                                    homeName
                                            .executes(new DelHomeCommand())
                            )
            );
        });
    }
}
