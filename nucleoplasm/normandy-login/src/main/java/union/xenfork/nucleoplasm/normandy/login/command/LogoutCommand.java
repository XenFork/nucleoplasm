package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccess;

public class LogoutCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        var impl = (EntityImplAccess) NucleoplasmServer.impl;
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            var e = (EntityAccessor)NucleoplasmServer.impl.find(player);
            e.setIsLogin(false);
            impl.save((Entity) e);
            return SINGLE_SUCCESS;
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
    }
}
