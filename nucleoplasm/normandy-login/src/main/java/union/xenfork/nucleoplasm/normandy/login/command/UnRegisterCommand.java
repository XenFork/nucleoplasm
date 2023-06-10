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
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

public class UnRegisterCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        String password = context.getArgument("password", String.class);
        if (player != null) {
            Entity entity = NucleoplasmServer.impl.find(player);
            try {
                String p = (String) entity.getClass().getDeclaredField("password").get(entity);
                if (p.equals(LockUtil.rightmove(password))) {
                    entity.getClass().getDeclaredField("password").set(entity, "");
                    entity.getClass().getDeclaredField("is_login").set(entity, false);
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
