package union.xenfork.nucleoplasm.api.event;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.minecraft.server.command.ServerCommandSource;

public interface CommandEvent {

    Event<Command> COMMAND_EVENT = EventFactory.createEventResult();

    interface Command {
        EventResult command(ParseResults<ServerCommandSource> parseResults, String command, ServerCommandSource serverCommandSource) throws CommandSyntaxException;
    }
}
