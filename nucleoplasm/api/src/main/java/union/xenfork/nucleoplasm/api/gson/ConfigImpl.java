package union.xenfork.nucleoplasm.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ConfigImpl {
    private Config config;
    public static final Gson gson = new GsonBuilder().setLenient().create();
    private final Path file;
    public ConfigImpl(Path path, String name) {
        var tp = path.resolve(name + ".json");
        file = tp;
        if (!Files.exists(tp)) {
            config = Config.of(c -> {
               c.authors = new ArrayList<>();
               c.authors.add("baka4n");
               c.authors.add("squid233");
               c.no_global = false;
            });
            save();
        } else {
            try {
                config = gson.fromJson(Files.newBufferedReader(file), Config.class);
            } catch (IOException ignored) {}
        }
    }

    public void save() {
        if (!Files.exists(file.getParent())) {
            try {
                Files.createDirectories(file.getParent());
            } catch (IOException ignored) {}
        }
        try {
            BufferedWriter bw = Files.newBufferedWriter(file);
            bw.write(gson.toJson(config));
            bw.close();
        } catch (IOException ignored) {}
    }

    public void tick(ServerWorld world) {
        long timeReference = world.getServer().getTimeReference();
        if (timeReference % 10000 == 0) {
            save();
        }
    }

    public void tick(ServerPlayerEntity player) {

    }

    public void save(MinecraftServer server) {
        save();
    }

    public Config getConfig() {
        return config;
    }
}
