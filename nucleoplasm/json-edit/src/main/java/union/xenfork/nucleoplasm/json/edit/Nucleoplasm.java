package union.xenfork.nucleoplasm.json.edit;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.loot.LootDataType;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import union.xenfork.nucleoplasm.json.edit.properties.NucleoplasmProperties;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public class Nucleoplasm implements ModInitializer {
    public static final Logger logger = LoggerFactory.getLogger("nucleoplasm_json_edit");
    public static final Path dir = FabricLoader.getInstance().getGameDir().resolve("json");
    public static final Path test_dir = FabricLoader.getInstance().getGameDir().resolve("test_json");
    public static final Path recipe = dir.resolve("recipes");
    public static final Path loot_table = dir.resolve("loot_table");

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resourceManager) -> {
            Collection<Identifier> ids = server.getLootManager().getIds(LootDataType.LOOT_TABLES);
            for (Identifier id : ids) {

            }
        });
    }

    public static void get(Path p) throws IOException {
        if (Files.isDirectory(p)) {
            Stream<Path> list = Files.list(p);
            get(p);
            list.close();
        } else {
            logger.info(String.valueOf(p));
        }
    }

    public static void outputJson(Map<Identifier, JsonElement> map, Path defaultPath) {
        //只有在第一次生成的时候才会生成
        if (!Files.exists(defaultPath)) {
            try {
                Files.createDirectories(defaultPath);
            } catch (IOException e) {
                logger.error("Failed to output json files", e);
            }
        }
        map.forEach((id, json) -> {
            Path namespace = defaultPath.resolve(id.getNamespace());
            if (!Files.exists(namespace)) {
                try {
                    Files.createDirectory(namespace);
                } catch (IOException e) {
                    logger.error("Failed to output json files", e);
                    return;
                }
            }
            String p = id.getPath();
            Path path = namespace;
            if (p.contains("/")) {
                String[] split = p.split("/");
                for (int i = 0; i < split.length; i++)
                    path = i == split.length - 1 ? path.resolve(split[i] + ".json") : path.resolve(split[i]);
            } else {
                path = path.resolve(p + ".json");
            }
            if (!Files.exists(path.getParent())) {
                try {
                    Files.createDirectories(path.getParent());
                } catch (IOException e) {
                    logger.error("Failed to output json files", e);
                    return;
                }
            }
            if (!Files.exists(path)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
                    writer.write(json.toString());
                } catch (IOException e) {
                    logger.error("Failed to output json files", e);
                }
            }
        });
    }

    public static void load(File file, StringBuilder namespace, StringBuilder path, Map<Identifier, File> iFile, String splitStr) {
        File[] files = file.listFiles();
        if (files != null && !Arrays.stream(files).toList().isEmpty()) {
            for (File file1 : files) {
                String replace = file
                    .getPath()
                    .replace("\\", "/")
                    .replace(file1.getParent(), "")
                    .replace("/", "");
                if (file1.isDirectory()) {
                    if (namespace == null) {
                        namespace = new StringBuilder();
                        namespace.append(replace);
                    } else if (path == null) {
                        path = new StringBuilder();
                        path.append(replace);
                    } else {
                        path.append("_").append(replace);
                    }
                    load(file1, new StringBuilder(namespace), path != null ? new StringBuilder(path) : null, iFile, splitStr);
                } else {
                    if (namespace == null) namespace = new StringBuilder("null");
                    if (path == null) path = new StringBuilder(replace.replace(".json", ""));
                    else path.append(splitStr).append(replace.replace(".json", ""));
                    iFile.put(Identifier.of(namespace.toString(), path.toString()), file1);
                }

            }
        }
    }

    public static void parser(Map<Identifier, File> iFile, Map<Identifier, JsonElement> map) {
        iFile.forEach((identifier, file) -> {
            try {
                JsonReader jr = new JsonReader(new FileReader(file));
                JsonElement parse = Streams.parse(jr);
                map.put(identifier, parse);
            } catch (FileNotFoundException e) {
                logger.error("Failed to read" + file, e);
                System.out.println(e.getMessage());
            }
        });
    }
}
