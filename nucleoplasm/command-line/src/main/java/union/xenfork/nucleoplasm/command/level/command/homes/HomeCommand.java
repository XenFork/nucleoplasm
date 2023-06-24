package union.xenfork.nucleoplasm.command.level.command.homes;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.command.level.face.EntityAccess;

public class HomeCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        String home_name = context.getArgument("home_name", String.class);
        if (player != null) {
            if (home_name == null) {
                EntityAccess access = (EntityAccess) NucleoplasmServer.impl.find(player);
                for (String home : access.getHomes()) {
                    player.sendMessage(Text.literal(home));
                }
            } else {
                EntityAccess access = (EntityAccess) NucleoplasmServer.impl.find(player);
                access.gotoHome(home_name, player);
            }
            return SINGLE_SUCCESS;
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
    }
}
