package union.xenfork.nucleoplasm.normandy.login.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public interface Impl {
    void register(CommandDispatcher<ServerCommandSource> dispatcher);
}
