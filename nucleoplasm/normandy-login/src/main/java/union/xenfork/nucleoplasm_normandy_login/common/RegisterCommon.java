package union.xenfork.nucleoplasm_normandy_login.common;

import com.github.artbits.quickio.api.Collection;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.ColorArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm_normandy_login.quickio.nnl.NNLPlayerEntity;
import union.xenfork.nucleoplasm_normandy_login.server.NucleoplasmNormandyLoginServer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class RegisterCommon implements Impl {

    public static int register(ServerCommandSource source, String p, String vp) {
        var db = NucleoplasmNormandyLoginServer.nnlPlayerDB;
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
