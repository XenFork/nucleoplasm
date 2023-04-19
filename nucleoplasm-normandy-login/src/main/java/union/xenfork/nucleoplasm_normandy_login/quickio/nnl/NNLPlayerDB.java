package union.xenfork.nucleoplasm_normandy_login.quickio.nnl;

import net.minecraft.entity.player.PlayerEntity;
import union.xenfork.nucleoplasm.api.quickio.utils.PlayerDB;
import union.xenfork.nucleoplasm_normandy_login.NucleoplasmNormandyLogin;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class NNLPlayerDB<T extends NNLPlayerEntity> extends PlayerDB<T> {
    public NNLPlayerDB(String name, Class<T> tClass) {
        super(name, tClass);
    }

    @Override
    public void add(PlayerEntity entity) {
        super.add(entity);
    }

    @Override
    public T get(PlayerEntity entity) {
        return super.get(entity);
    }

    public void setLoginOut(PlayerEntity entity) {
        T entity1 = findEntity(entity);
        entity1.Last_join_time = Objects.requireNonNull(entity.getServer()).getTimeReference();
        entity1.isLogin = false;
    }

    @Override
    public void add(T t) {
        super.add(t);
    }

    public void add2(union.xenfork.nucleoplasm.api.quickio.PlayerEntity entity) {
        try {
            collection.save(tClass.getDeclaredConstructor().newInstance(entity));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            NucleoplasmNormandyLogin.logger.error(e.getMessage());
        }
    }

    @Override
    public void remove(PlayerEntity entity) {
        super.remove(entity);
    }

    @Override
    public T findEntity(PlayerEntity entity) {
        return super.findEntity(entity);
    }

    @Override
    public void close() {
        super.close();
    }
}
