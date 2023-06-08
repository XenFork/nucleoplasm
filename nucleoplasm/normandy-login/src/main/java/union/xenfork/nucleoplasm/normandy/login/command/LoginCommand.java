package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

public class LoginCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = context.getSource().getPlayer();
        String password = context.getArgument("password", String.class);
        if (player != null) {
            NucleoplasmEntity entity = NucleoplasmServer.nnl.findEntity(player);
            if (entity.is_login) {
                throw new SimpleCommandExceptionType(new LiteralMessage("you're logged in!")).create();
            }
            if (entity.password == null || entity.password.isEmpty()) {
                throw new SimpleCommandExceptionType(new LiteralMessage("You're not registered yet!")).create();
            }
            if (password.equals(entity.password)) {
                entity.is_login = true;
                player.setInvulnerable(false);
                return SINGLE_SUCCESS;
            }
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
        return 0;
    }
}
