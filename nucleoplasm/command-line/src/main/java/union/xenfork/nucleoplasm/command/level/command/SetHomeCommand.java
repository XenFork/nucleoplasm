package union.xenfork.nucleoplasm.command.level.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.command.level.face.EntityAccess;

public class SetHomeCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String home_name = context.getArgument("home_name", String.class);
        ServerPlayerEntity player = context.getSource().getPlayer();
        EntityAccess access = (EntityAccess) NucleoplasmServer.impl.find(player);
        if (home_name != null) {
            if (!access.getHomes().contains(home_name)) {
                access.setHome(home_name, player);
                return SINGLE_SUCCESS;
            } else {
                throw new SimpleCommandExceptionType(new LiteralMessage("don't have %s".formatted(home_name))).create();
            }
        }
        return 0;
    }
}
