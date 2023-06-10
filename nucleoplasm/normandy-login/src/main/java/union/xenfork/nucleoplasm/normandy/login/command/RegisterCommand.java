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
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.face.EntityImplAccess;
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

public class RegisterCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            var impl = (EntityImplAccess) NucleoplasmServer.impl;
            var entity = (EntityAccessor)NucleoplasmServer.impl.find(player);
            String p = entity.getPassword();
            if (p == null || p.isEmpty()) {
                String password = context.getArgument("password", String.class);
                String vp = context.getArgument("confirm_password", String.class);
                if (password.equals(vp)) {
                    entity.setPassword(LockUtil.rightmove(password));
                    entity.setIsLogin(true);
                    player.setInvulnerable(false);
                    impl.save((Entity) entity);
                    player.sendMessage(Text.literal("Register successful!"));
                    return SINGLE_SUCCESS;
                }
            } else {
                throw new SimpleCommandExceptionType(new LiteralMessage("You have already registered!")).create();
            }
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
        return 0;
    }
}
