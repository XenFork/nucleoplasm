package union.xenfork.nucleoplasm.normandy.login.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static union.xenfork.nucleoplasm.api.sql.NucleoplasmLoader.gson;

public class CfgImpl {
    public Cfg entity;
    public Path config;

    public CfgImpl() {
        config = FabricLoader.getInstance().getConfigDir().resolve("nucleoplasm/normandy/login/config.json");
        Path parent = config.getParent();
        if (!Files.exists(parent)) try {
            Files.createDirectories(parent);
        } catch (IOException ignored) {}
        if (!Files.exists(config)) {
            entity = new Cfg();
            entity.i = 25;
            save();
        } else {
            entity = load();
        }
    }

    public void save() {
        try (BufferedWriter bw = Files.newBufferedWriter(config)) {
            bw.write(gson.toJson(entity));
        } catch (IOException ignored) {}
    }

    public Cfg load() {
        try {
            return gson.fromJson(Files.newBufferedReader(config), Cfg.class);
        } catch (IOException ignored) {}
        return null;
    }

    public Cfg getEntity() {
        return entity;
    }
}
