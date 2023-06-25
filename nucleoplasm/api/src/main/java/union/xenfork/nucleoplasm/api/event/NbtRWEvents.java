package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class NbtRWEvents {
    public static final Event<ReadServerPlayerNbt> readServerPlayerNbtEvent = EventFactory.createArrayBacked(
            ReadServerPlayerNbt.class,
            readServerPlayerNbts -> (nbt , player) -> {
                for (ReadServerPlayerNbt playerNbt : readServerPlayerNbts) {
                    playerNbt.read(nbt, player);
                }
            }
    );
    public static final Event<WriteServerPlayerNbt> writeServerPlayerNbtEvent = EventFactory.createArrayBacked(
            WriteServerPlayerNbt.class,
            writeServerPlayerNbts -> (nbt , player) -> {
                for (WriteServerPlayerNbt playerNbt : writeServerPlayerNbts) {
                    playerNbt.write(nbt, player);
                }
            }
    );
    public interface ReadServerPlayerNbt {
        void read(NbtCompound nbt, ServerPlayerEntity player);
    }
    public interface WriteServerPlayerNbt {
        void write(NbtCompound nbt, ServerPlayerEntity player);
    }
}
