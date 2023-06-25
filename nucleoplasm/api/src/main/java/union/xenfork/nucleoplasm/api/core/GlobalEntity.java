package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.core.IOEntity;

import java.util.function.Consumer;

public class GlobalEntity extends IOEntity {
    public String gamemode;
    public static GlobalEntity of(Consumer<GlobalEntity> consumer) {
        GlobalEntity t = new GlobalEntity();
        consumer.accept(t);
        return t;
    }
}
