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
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

import java.lang.reflect.Field;

public class ChangeCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        String password = context.getArgument("old_password", String.class);
        String new_password = context.getArgument("new_password", String.class);
        if (player != null) {
            Entity entity = NucleoplasmServer.impl.find(player);
            try {
                String p = (String) entity.getClass().getDeclaredField("password").get(entity);
                if (p.equals(password)) {
                    entity.getClass().getDeclaredField("password").set(entity, new_password);
                    player.sendMessage(Text.literal("You have changed the password, please enter the password to try"));
                    entity.getClass().getDeclaredField("is_login").set(entity, false);
                    return SINGLE_SUCCESS;
                } else {
                    throw new SimpleCommandExceptionType(new LiteralMessage("Wrong password!")).create();
                }
            } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
        return 0;
    }
}
