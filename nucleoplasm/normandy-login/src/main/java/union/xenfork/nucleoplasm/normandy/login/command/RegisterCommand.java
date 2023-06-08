package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

public class RegisterCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            NucleoplasmEntity entity = NucleoplasmServer.nnl.findEntity(player);
            if (entity.password.isEmpty()) {
                String p = context.getArgument("password", String.class);
                String vp = context.getArgument("confirm_password", String.class);
                if (p.equals(vp)) {
                    entity.password = p;
                    entity.is_login = true;
                    player.setInvulnerable(false);
                    return Command.SINGLE_SUCCESS;
                }
            } else {
                throw new SimpleCommandExceptionType(new LiteralMessage("You have already registered!")).create();
            }
        }
        return 0;
    }
}
