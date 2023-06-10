package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccess;
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

public class LoginCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayer();
        String password = context.getArgument("password", String.class);
        if (player != null) {
            var impl = (EntityImplAccess) NucleoplasmServer.impl;
            var entity = (EntityAccessor)NucleoplasmServer.impl.find(player);
            boolean is_login = entity.getIsLogin();
            if (is_login) throw new SimpleCommandExceptionType(new LiteralMessage("You're logged in!")).create();
            String p = entity.getPassword();
            if (p == null || p.isEmpty()) throw new SimpleCommandExceptionType(new LiteralMessage("You're not registered yet!")).create();
            if (LockUtil.rightmove(password).equals(p)) {
                entity.setIsLogin(true);
                player.setInvulnerable(true);
                player.sendMessage(Text.literal("Login successful!"));
                impl.save((Entity) entity);
                return SINGLE_SUCCESS;
            } else {
                throw new SimpleCommandExceptionType(new LiteralMessage("Wrong password!")).create();
            }
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
    }
}
