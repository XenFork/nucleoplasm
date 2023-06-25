package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.core.IOEntity;

import java.util.function.Consumer;

public class ServerEntity extends IOEntity {
    public String threadName;
    public static ServerEntity of(Consumer<ServerEntity> consumer) {
        ServerEntity serverEntity = new ServerEntity();
        consumer.accept(serverEntity);
        return serverEntity;
    }
}
