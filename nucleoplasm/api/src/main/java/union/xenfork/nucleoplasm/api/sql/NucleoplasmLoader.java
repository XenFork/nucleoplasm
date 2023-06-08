package union.xenfork.nucleoplasm.api.sql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class NucleoplasmLoader<T extends NucleoplasmEntity> {
    public final Map<String, NucleoplasmEntity> playerEntity = new HashMap<>();
    public static final Path path = FabricLoader.getInstance().getConfigDir().resolve("nucleoplasm/data");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path data;
    private final Class<T> tClass;
    public NucleoplasmLoader(String dataName, Class<T> tClass) {
        data = path.resolve(dataName);
        this.tClass = tClass;
        if (!Files.exists(data)) try {
            Files.createDirectories(data);
        } catch (IOException ignored) {}
        load();
    }

    public void load() {
        try (Stream<Path> list = Files.list(data)) {
            for (Path p : list.toList()) {
                try (BufferedReader br = Files.newBufferedReader(p)) {
                    String[] split = p.toString().replace("\\", "/").split("/");
                    playerEntity.put(de(split[split.length - 1]), gson.fromJson(br, tClass));
                }
            }
        } catch (IOException ignored) {}
    }

    public void save() {
        for (Map.Entry<String, NucleoplasmEntity> entry : playerEntity.entrySet()) {
            String entityName = entry.getKey();
            NucleoplasmEntity entity = entry.getValue();
            Path resolve = data.resolve(en(entityName));
            if (!Files.exists(resolve)) {
                try(BufferedWriter bw = Files.newBufferedWriter(resolve)) {
                    String json = gson.toJson(entity);
                    bw.write(json);
                } catch (IOException ignored) {}
            }
        }
    }

    public NucleoplasmEntity findEntity(ServerPlayerEntity entity) {
        String entityName = entity.getEntityName();
        if (!playerEntity.containsKey(entityName)) {
            NucleoplasmEntity t = T.of(it -> {
                it.uuid = entity.getUuid().toString();
                it.fly = entity.isFallFlying();
                it.login_time = Objects.requireNonNull(entity.getServer()).getTimeReference();
                it.first_join_time = Objects.requireNonNull(entity.getServer()).getTimeReference();
                it.gamemode = entity.interactionManager.getGameMode().name();
                it.is_invincible = entity.isInvulnerable();
                it.is_invisible = entity.isInvisible();
                it.is_login = false;
                it.password = "";
                it.x = entity.getX();
                it.y = entity.getY();
                it.z = entity.getZ();
            });
            playerEntity.put(entityName, t);
            return t;
        } else {
            return playerEntity.get(entityName);
        }
    }

    public NucleoplasmEntity findEntity(PlayerEntity entity) {
        return playerEntity.get(entity.getEntityName());
    }

    public String de(String message) {
        return message.replace(".json", "");
    }

    public String en(String message) {
        return message + ".json";
    }
}
