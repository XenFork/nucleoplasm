package union.xenfork.nucleoplasm.normandy.login.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class RegisterCommon implements Impl {

    public static int register(ServerCommandSource source, String p, String vp) {
        var db = NucleoplasmServer.nnlPlayerDB;
        final PlayerEntity player = source.getPlayer();
        db.register(player, p, vp);
        return 1;
    }

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("register")
                .then(argument("password", greedyString())
                        .then(argument("verify_password", greedyString())
                                .executes(ctx -> register(ctx.getSource(), getString(ctx, "password"), getString(ctx,"verify_password"))))));
    }
}
