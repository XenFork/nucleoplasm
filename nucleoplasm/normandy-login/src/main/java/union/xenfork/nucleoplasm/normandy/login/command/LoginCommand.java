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
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

public class LoginCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        var player = context.getSource().getPlayer();
        String password = context.getArgument("password", String.class);
        if (player != null) {
            Entity entity = NucleoplasmServer.impl.find(player);
            try {
                boolean is_login = (boolean) entity.getClass().getDeclaredField("is_login").get(entity);
                if (is_login) throw new SimpleCommandExceptionType(new LiteralMessage("you're logged in!")).create();
                String p = (String) entity.getClass().getDeclaredField("password").get(entity);
                if (p == null || p.isEmpty()) throw new SimpleCommandExceptionType(new LiteralMessage("You're not registered yet!")).create();
                if (LockUtil.rightmove(password).equals(p)) {
                    entity.getClass().getDeclaredField("is_login").set(entity, true);
                    player.setInvulnerable(true);
                    player.sendMessage(Text.literal("login success!"));
                    return SINGLE_SUCCESS;
                } else {
                    throw new SimpleCommandExceptionType(new LiteralMessage("Wrong password!")).create();
                }
            } catch (IllegalAccessException | NoSuchFieldException ignored) {}
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
        return 0;
    }
}
