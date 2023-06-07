package union.xenfork.nucleoplasm_normandy_login.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm_normandy_login.server.NucleoplasmNormandyLoginServer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class LoginCommon implements Impl {
//    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
//
//    }

    public static int login(ServerCommandSource source, String password) {
        PlayerEntity player = source.getPlayer();
        NucleoplasmNormandyLoginServer.nnlPlayerDB.login(player, password);
        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("login")
                .then(argument("password", greedyString())
                                .executes(ctx -> login(ctx.getSource(), getString(ctx, "password")))));
    }
}
