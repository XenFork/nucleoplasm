package union.xenfork.nucleoplasm.api.event;

import com.mojang.brigadier.ParseResults;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class CommandEvents {
    public static final Event<Execute> execute = EventFactory.createArrayBacked(Execute.class, executes -> (parseResults, command, cir) -> {
        for (Execute execute : executes) {
            execute.execute(parseResults, command, cir);
        }
    });
    public interface Execute {
        void execute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfoReturnable<Integer> cir);
    }
}
