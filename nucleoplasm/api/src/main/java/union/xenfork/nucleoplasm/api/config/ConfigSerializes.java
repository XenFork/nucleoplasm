package union.xenfork.nucleoplasm.api.config;

import union.xenfork.nucleoplasm.api.db.SQLImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;


public class ConfigSerializes implements SQLImpl {
    public String language;
    public File dataFile;
    public String key;

    public static ConfigSerializes of(Consumer<ConfigSerializes> consumer, Path p) {
        ConfigSerializes configSerializes = new ConfigSerializes();
        consumer.accept(configSerializes);
        try {
            configSerializes.save(p, false, null);
        } catch (IOException ignored) {}
        return configSerializes;
    }

    @Override
    public void save(Path p, boolean isLock, String key) throws IOException {
        SQLImpl.super.save(p, isLock, key);
    }
}
