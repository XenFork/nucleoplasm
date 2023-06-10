package union.xenfork.nucleoplasm.normandy.login.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.api.NucleoplasmServer;
import union.xenfork.nucleoplasm.api.core.Entity;
import union.xenfork.nucleoplasm.api.sql.NucleoplasmEntity;
import union.xenfork.nucleoplasm.normandy.login.face.EntityAccessor;
import union.xenfork.nucleoplasm.normandy.login.mixin.MixinEntity;
import union.xenfork.nucleoplasm.normandy.login.utils.LockUtil;

import java.lang.reflect.Field;

public class ChangeCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        String password = context.getArgument("old_password", String.class);
        String new_password = context.getArgument("new_password", String.class);
        if (player != null) {
            var entity = (EntityAccessor)NucleoplasmServer.impl.find(player);
            String p = entity.getPassword();
            if (p.equals(password)) {
                entity.setPassword(new_password);

                player.sendMessage(Text.literal("You have changed the password, please enter the password to try"));
                entity.setIsLogin(false);
                return SINGLE_SUCCESS;
            } else {
                throw new SimpleCommandExceptionType(new LiteralMessage("Wrong password!")).create();
            }
        } else {
            throw new SimpleCommandExceptionType(new LiteralMessage("Go away, you're not a human being")).create();
        }
    }
}
