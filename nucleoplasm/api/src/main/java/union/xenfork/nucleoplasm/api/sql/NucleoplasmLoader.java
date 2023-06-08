package union.xenfork.nucleoplasm.api.sql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NucleoplasmLoader<T extends NucleoplasmEntity> {
    public final Map<String, NucleoplasmEntity> playerEntity = new HashMap<>();
    public static final Path path = FabricLoader.getInstance().getConfigDir().resolve("nucleoplasm/data");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path data;
    private final Class<T> tClass;
    public NucleoplasmLoader(String dataName, Class<T> tClass) {
        data = path.resolve(dataName);
        this.tClass = tClass;
        if (Files.exists(data)) try {
            Files.createDirectories(data);
        } catch (IOException ignored) {}
    }

    public void findEntity(ServerPlayerEntity entity) {
        String entityName = entity.getEntityName();
//        Path path1 = data.resolve(entityName + ".json");
        if (!playerEntity.containsKey(entityName)) {
            try {
                T t = tClass.getDeclaredConstructor().newInstance();
                t.uuid = entity.getUuid().toString();
                t.fly = entity.isFallFlying();
                t.first_join_time = Objects.requireNonNull(entity.getServer()).getTimeReference();

                t.gamemode = entity.interactionManager.getGameMode().name();
                t.is_invincible = entity.isInvulnerable();
                t.is_invisible = entity.isInvisible();
                t.is_login = false;
                playerEntity.put(t,)
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ignored) {}
        }
    }
}
