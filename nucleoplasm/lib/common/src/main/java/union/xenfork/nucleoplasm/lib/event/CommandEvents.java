package union.xenfork.nucleoplasm.lib.event;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import union.xenfork.nucleoplasm.lib.event.impl.Event;
import union.xenfork.nucleoplasm.lib.event.impl.EventFactory;

public class CommandEvents {
    public static final Event<Execute> EXECUTE_EVENT = EventFactory.createArrayBacked(
            Execute.class,
            executes -> (parseResults, command, serverCommandSource) -> {
                for (Execute execute : executes) {
                    Integer result = execute.execute(parseResults, command, serverCommandSource);
                    if (!result.equals(Command.SINGLE_SUCCESS)) {
                        return result;
                    }
                }
                return Command.SINGLE_SUCCESS;
            }
    );
    @FunctionalInterface
    public interface Execute {
        Integer execute(ParseResults<ServerCommandSource> parseResults, String command, ServerCommandSource serverCommandSource) throws CommandSyntaxException;
    }
}
