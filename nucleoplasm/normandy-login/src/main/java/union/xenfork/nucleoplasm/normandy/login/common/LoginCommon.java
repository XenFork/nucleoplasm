package union.xenfork.nucleoplasm.normandy.login.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class LoginCommon {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("login")
                .then(argument("password", greedyString())
                        .executes(ctx -> login(ctx.getSource(), getString(ctx, "password")))));
    }

    public static int login(ServerCommandSource source, String password) {
        PlayerEntity player = source.getPlayer();
        NucleoplasmServer.nnlPlayerDB.login(player, password);
        return 1;
    }

}
