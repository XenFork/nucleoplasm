package union.xenfork.nucleoplasm.normandy.login.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class RegisterCommon {

    public static int register(ServerCommandSource source, String p, String vp) {
        NucleoplasmEntity entity = NucleoplasmServer.nnl.findEntity(source.getPlayer());
        ServerPlayerEntity player = source.getPlayer();
        if (p.equals(vp) && entity.password.isEmpty() && player != null) {
            entity.password = p;
            entity.is_login = true;
            player.setInvulnerable(false);
        }
        return 1;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("register")
                .then(argument("password", greedyString())
                        .then(argument("verify_password", greedyString())
                                .executes(ctx -> register(ctx.getSource(), getString(ctx, "password"), getString(ctx,"verify_password"))))));
    }
}
