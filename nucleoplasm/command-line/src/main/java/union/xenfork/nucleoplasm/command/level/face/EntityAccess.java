package union.xenfork.nucleoplasm.command.level.face;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;

public interface EntityAccess {

    default void setHome(String homeIndex, ServerPlayerEntity player) {}

    default void gotoHome(String homeIndex, ServerPlayerEntity player) {}
    default void delHome(String homeIndex) {}

    default Set<String> getHomes() {throw new AssertionError();}
}
