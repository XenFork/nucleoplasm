package union.xenfork.nucleoplasm.normandy.login.quickio.nnl;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import union.xenfork.nucleoplasm.api.quickio.utils.PlayerDB;
import union.xenfork.nucleoplasm.normandy.login.NucleoplasmServer;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class NNLPlayerDB<T extends NNLPlayerEntity> extends PlayerDB<T> {
    public NNLPlayerDB(String name, Class<T> tClass) {
        super(name, tClass);
    }

    @Override
    public void add(PlayerEntity entity) {
        super.add(entity);
        T entity1 = findEntity(entity);
        entity1.isLogin = false;
        entity1.first_join_time = entity.getServer().getTimeReference();
        entity1.Last_join_time = entity.getServer().getTimeReference();
        entity1.health = entity.getHealth();
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

    public void register(PlayerEntity entity, String password, String verify) {
        T entity1 = findEntity(entity);
        if (password.equals(verify) && password.isEmpty()) {
            entity1.password = password;
            entity1.isLogin = true;
            entity.sendMessage(Text.of("register success"));
            return;
        }
        entity.sendMessage(Text.of("You have already registered"));
    }

    public void login(PlayerEntity entity, String password) {
        T entity1 = findEntity(entity);
        if (entity1.password.equals(password)) {
            entity1.isLogin = true;
            entity.sendMessage(Text.of("login success"));
            return;
        }
        entity.sendMessage(Text.of("Wrong password"));
    }

    @Override
    public void add(T t) {
        super.add(t);
    }

    public void add2(union.xenfork.nucleoplasm.api.quickio.PlayerEntity entity) {
        try {
            collection.save(tClass.getDeclaredConstructor().newInstance(entity));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            NucleoplasmServer.logger.error(e.getMessage());
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
