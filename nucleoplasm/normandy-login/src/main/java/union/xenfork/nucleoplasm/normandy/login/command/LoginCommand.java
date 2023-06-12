package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

public class LoginCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayer();
        String password = context.getArgument("password", String.class);
        if (player != null) {
            var entity = (EntityAccessor)NucleoplasmServer.impl.find(player);
            boolean is_login = entity.getIsLogin();
            if (is_login) throw new SimpleCommandExceptionType(new LiteralMessage("You're logged in!")).create();
            if (entity.getPassword() == null || entity.getPassword().isEmpty()) throw new SimpleCommandExceptionType(new LiteralMessage("You're not registered yet!")).create();
            if (LockUtil.rightmove(password).equals(entity.getPassword())) {
                entity.setIsLogin(true);
                player.setInvulnerable(true);
                player.sendMessage(Text.literal("Login successful!"));
                return SINGLE_SUCCESS;
            } else {
                throw new SimpleCommandExceptionType(new LiteralMessage("Wrong password!")).create();
            }
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
    }
}
