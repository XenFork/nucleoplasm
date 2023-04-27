package union.xenfork.nucleoplasm.api.quickio.utils;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import union.xenfork.nucleoplasm.api.NucleoplasmApi;
import union.xenfork.nucleoplasm.api.quickio.PlayerEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.UUID;

public class PlayerDB<T extends PlayerEntity> {
    private final DB db;
    public final Collection<T> collection;
    public final Class<T> tClass;
    public PlayerDB(String name, Class<T> tClass) {
        db = QuickIO.usingDB(name);
        this.tClass = tClass;
        collection = db.collection(tClass);
    }

    public T findEntity(net.minecraft.entity.player.PlayerEntity entity) {
        return collection.findOne(entity1 -> entity1.player_name.equals(entity.getEntityName()));
    }

    public void add(T t) {
        collection.save(t);
    }

    public void add(net.minecraft.entity.player.PlayerEntity entity) {
        try {
            T t = tClass.getDeclaredConstructor().newInstance();
            t.player_name = entity.getEntityName();
            t.uuid = entity.getUuid();
            t.x = entity.getX();
            t.y = entity.getY();
            t.z = entity.getZ();
            collection.save(t);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            NucleoplasmApi.logger.info(e.getMessage());
        }
    }

    public void remove(net.minecraft.entity.player.PlayerEntity entity) {
        collection.delete(t -> t.player_name.equals(entity.getEntityName()));
    }

    public T get(net.minecraft.entity.player.PlayerEntity entity) {
        return collection.findOne(t -> t.player_name.equals(entity.getEntityName()));
    }

    public void close() {
        db.close();
    }
}
