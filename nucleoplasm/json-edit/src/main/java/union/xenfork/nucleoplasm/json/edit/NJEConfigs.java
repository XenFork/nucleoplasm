package union.xenfork.nucleoplasm.json.edit;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class NJEConfigs extends HashMap<String, Object> {
    public final Type t = new TypeToken<NJEConfigs>(){}.getType();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String paths;
    public NJEConfigs(String paths) {
        super();
        this.paths = paths;
    }
    public NJEConfigs(String paths,List<String> ks, List<String> vs) {
        super();
        this.paths = paths;
        for (int i = 0; i < ks.size(); i++) {
            var k = ks.get(i);
            if (vs.get(i) == null) {
                super.put(ks.get(i), null);
            } else {
                super.put(ks.get(i), vs.get(i));
            }
        }
    }

    public void set(String k, Object v) {
        super.put(k, v);
        try {
            save();
        } catch (IOException ignored) {}
    }

    public void clear() {
        super.clear();
    }

    public void clear(String k) {
        super.remove(k);
    }

    public void save() throws IOException {
        Path config = FabricLoader.getInstance().getConfigDir().resolve(paths);
        Path parent = config.getParent();
        if (!Files.exists(parent)) Files.createDirectories(parent);
        BufferedWriter bw = Files.newBufferedWriter(config);
        String json = gson.toJson(this, t);
        bw.write(json);
        bw.close();
    }

    public void tick() throws IOException {
        load();
    }

    public void load() throws IOException {
        BufferedReader br = Files.newBufferedReader(FabricLoader.getInstance().getConfigDir().resolve(paths));
        super.clear();
        super.putAll(gson.fromJson(br, t));
    }

    public void create() throws IOException {
        Path config = FabricLoader.getInstance().getConfigDir().resolve(paths);
        Path parent = config.getParent();
        if (!Files.exists(parent)) Files.createDirectories(parent);
        if (!Files.exists(config)) {
            super.put("isLoader", false);
            save();
        } else {
            load();
        }
    }
}
