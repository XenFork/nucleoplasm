package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccess;

public class IpCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
        if (serverPlayer != null) {
            ServerPlayerEntity player = context.getArgument("player", ServerPlayerEntity.class);
            EntityAccessor entity = (EntityAccessor) NucleoplasmServer.impl.find(player);
            StringBuilder sb = new StringBuilder();
            for (String ip : entity.getIps()) {
                sb.append(ip).append("\n");
            }
            player.sendMessage(Text.literal(sb.toString()));
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }

        return 0;
    }
}
