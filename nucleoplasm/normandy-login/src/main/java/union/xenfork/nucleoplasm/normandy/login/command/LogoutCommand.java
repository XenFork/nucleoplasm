package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;

public class LogoutCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerPlayerEntity player1 = context.getArgument("player", ServerPlayerEntity.class);
        if (player != null) {
            var e = player1 != null ?
                    (EntityAccessor)NucleoplasmServer.impl.find(player1) :
                    (EntityAccessor)NucleoplasmServer.impl.find(player);
            e.setIsLogin(false);
            return SINGLE_SUCCESS;
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }

    }
}
