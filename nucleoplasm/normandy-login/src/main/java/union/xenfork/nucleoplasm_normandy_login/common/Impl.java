package union.xenfork.nucleoplasm_normandy_login.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

public interface Impl {
    void register(CommandDispatcher<ServerCommandSource> dispatcher);
}
