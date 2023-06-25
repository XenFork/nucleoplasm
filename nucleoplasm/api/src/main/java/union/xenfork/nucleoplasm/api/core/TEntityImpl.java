package union.xenfork.nucleoplasm.api.core;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.core.QuickIO;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public class TEntityImpl<T extends IOEntity> {
    private final Class<T> tClass;
    private final Config config;
    public TEntityImpl(String dbName, Path path, Class<T> tClass) {
        this(dbName, path.toString(), tClass);
    }

    public TEntityImpl(String dbName, String path, Class<T> tClass) {
        this.tClass = tClass;
        this.config = Config.of(c -> {
            c.name(dbName);
            c.path(path);
            c.cache(16L * 1024 * 1024);
        });
        try(DB db = QuickIO.usingDB(config)) {
            Collection<T> collection = db.collection(tClass);
            List<T> all = collection.findAll();
            if (all.isEmpty()) {
                try {
                    collection.save(tClass.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException ignored) {
                }
            }
        }
    }

    public void reset(T t) {//重新设置数据
        try(DB db = QuickIO.usingDB(config)) {
            Collection<T> collection = db.collection(tClass);
            collection.deleteAll();
            collection.save(t);
        }
    }

    public T find() {
        try(DB db = QuickIO.usingDB(config)) {
            Collection<T> collection = db.collection(tClass);
            return collection.findAll().get(0);
        }
    }

    public void  edit(Consumer<T> consumer) {
        T t = find();
        consumer.accept(t);
        try(DB db = QuickIO.usingDB(config)) {
            Collection<T> collection = db.collection(tClass);
            collection.save(t);
        }
    }
}
