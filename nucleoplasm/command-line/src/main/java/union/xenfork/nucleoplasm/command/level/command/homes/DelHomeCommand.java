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

public class DelHomeCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        String homeName = context.getArgument("home_name", String.class);
        if (player != null) {
            if (homeName != null) {
                EntityAccess access = (EntityAccess) NucleoplasmServer.impl.find(player);
                if (access.getHomes().contains(homeName)) {
                    access.delHome(homeName);
                    player.sendMessage(Text.literal("del home%ssuccess!".formatted(homeName)));
                    return SINGLE_SUCCESS;
                } else {
                    throw new SimpleCommandExceptionType(new LiteralMessage("don't del home")).create();
                }
            }
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
        return 0;
    }
}
