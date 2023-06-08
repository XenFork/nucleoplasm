package union.xenfork.nucleoplasm.normandy.login.common;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.GiveCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.*;

public class RegisterCommon implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        NucleoplasmEntity entity = NucleoplasmServer.nnl.findEntity(player);
        String p = context.getArgument("password", String.class);
        String vp = context.getArgument("verify_password", String.class);
        if (p.equals(vp) && entity.password.isEmpty() && player != null) {
            entity.password = p;
            entity.is_login = true;
            player.setInvulnerable(false);
        }
        return Command.SINGLE_SUCCESS;
    }
}
