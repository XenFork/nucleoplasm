package union.xenfork.nucleoplasm.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.nbt.NbtCompound;

public class NbtRWEvents {
    public static final Event<ReadServerPlayerNbt> readServerPlayerNbtEvent = EventFactory.createArrayBacked(
            ReadServerPlayerNbt.class,
            readServerPlayerNbts -> nbt -> {
                for (ReadServerPlayerNbt playerNbt : readServerPlayerNbts) {
                    playerNbt.read(nbt);
                }
            }
    );
    public static final Event<WriteServerPlayerNbt> writeServerPlayerNbtEvent = EventFactory.createArrayBacked(
            WriteServerPlayerNbt.class,
            writeServerPlayerNbts -> nbt -> {
                for (WriteServerPlayerNbt playerNbt : writeServerPlayerNbts) {
                    playerNbt.write(nbt);
                }
            }
    );
    public interface ReadServerPlayerNbt {
        void read(NbtCompound nbt);
    }
    public interface WriteServerPlayerNbt {
        void write(NbtCompound nbt);
    }
}
